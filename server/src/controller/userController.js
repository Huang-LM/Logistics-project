const jwt = require('jsonwebtoken')
const nodemailer = require('nodemailer')
const { createUser, getUserInfo, getUserRole, updatePassword, getUserMenu, getUserList, updateFindPassword, getRoleName, updateuser, deleteuser, examine } = require('../service/userService')
const { userRegisterError, userLoginError, userNotApproved } = require('../constant/errType')
const { JWT_SECRET } = process.env

let MAILCODE = 0;

class UserController {
  // 注册用户
  async register(ctx, next) {
    const { username, password, phone, gender, email, verify, role, code } = ctx.request.body
    try {
      if (code !== MAILCODE) {
        ctx.body = {
          code: 0,
          message: "验证码错误，请重新申请",
        }
      } else {
        const res = await createUser(username, password, phone, gender, email, verify, role)
        ctx.body = {
          code: 1,
          status: 200,
          id: res.id,
          username: res.username,
          message: "用户注册成功",
        }
      }
    } catch (err) {
      console.log(err)
      ctx.app.emit('error', userRegisterError, ctx)
    }
  }

  async add(ctx, next) {
    const { username, password, phone, gender, email, verify, role, code } = ctx.request.body
    try {
      const res = await createUser(username, password, phone, gender, email, verify, role)
      ctx.body = {
        code: 1,
        status: 200,
        id: res.id,
        username: res.username,
        message: "用户注册成功",
      }
    } catch (err) {
      console.log(err)
      ctx.app.emit('error', userRegisterError, ctx)
    }
  }

  // 发送验证码
  async mailCode(ctx) {
    const { email } = ctx.request.body
    const userEmail = 'mekatop@qq.com'
    const transporter = nodemailer.createTransport({
      service: 'qq',
      secureConnection: true,
      auth: {
        user: userEmail,
        pass: 'tyuvptsotbozbdhe'  //这个是开启`POP3/SMTP/IMAP`的授权码
      }
    })

    const code = Math.random().toString().slice(2, 6)
    // console.log(code);
    MAILCODE = code //随机验证码
    const mailOptions = {
      from: userEmail,
      cc: userEmail,
      to: email,
      subject: '验证码',
      text: '说明内容',
      html: '<h2>【码头船只出行平台】</h2> 验证码：<span>' + code + '</span>'
    }
    try {
      await transporter.sendMail(mailOptions)
      ctx.body = {
        code: 1,
        message: '邮箱验证码发送成功！',
      }
    } catch (e) {
      ctx.body = {
        code: 0,
        message: '邮箱验证码发送失败！',
      }
    }

  }

  // 找回密码
  async findPassword(ctx) {
    const { email, password } = ctx.request.body
    if (await updateFindPassword(email, password)) {
      ctx.body = {
        code: 1,
        status: 200,
        data: {
          message: "密码修改成功",
        }
      }
    } else {
      ctx.body = {
        code: 0,
        data: {
          message: "密码修改失败",
        }
      }
    }
  }

  // 找回密码时的验证
  async checkCode(ctx) {
    const { code } = ctx.request.body
    if (code !== MAILCODE) {
      ctx.body = {
        code: 0,
        message: "验证码错误，请重新申请",
      }
    } else {
      ctx.body = {
        code: 1,
        message: "验证通过",
      }
    }
  }

  // 登录操作
  async login(ctx, next) {
    const { username } = ctx.request.body

    try {
      const { password, ...res } = await getUserInfo({ username })
      // console.log(res);

      // 未通过审核无法登录
      if (!res.verify) {
        // ctx.app.emit('error', userNotApproved, ctx)
        return ctx.body = {
          code: 0,
          status: 200,
          data: {
            message: "用户还未通过审核，请等待审核"
          }
        }
      }

      // await getUerRole(res.id)

      ctx.body = {
        code: 1,
        status: 200,
        data: {
          userId: res.id,
          username: username,
          message: "用户登录成功",
          token: jwt.sign(res, JWT_SECRET, { expiresIn: '1d' }),
        }
      }
    } catch (err) {
      console.log(err)
      ctx.app.emit('error', userLoginError, ctx)
    }
  }

  // 修改密码
  async updatapw(ctx, next) {
    const id = ctx.request.body.id
    const password = ctx.request.body.password
    const new_password = ctx.request.body.new_password

    // console.log(11111);
    if (await updatePassword(id, password, new_password)) {
      ctx.body = {
        code: 1,
        status: 200,
        message: "密码修改成功",
      }
    } else {
      ctx.body = {
        code: 0,
        message: "密码修改失败",
      }
    }
  }

  // 获取该用户菜单
  async getMenu(ctx) {
    const id = ctx.request.body.id
    const res = await getUserMenu(id)
    ctx.body = {
      data: res
    }
  }

  // 通过user_id获取用户信息
  async getUser(ctx) {
    const { id } = ctx.request.body
    const res = await getUserInfo({ id })
    ctx.body = {
      data: res
    }
  }

  // 查询获取用户列表
  async getList(ctx) {
    const { id, username, phone, role, gender, page, pageSize } = ctx.request.body

    const { list, total } = await getUserList(id, username, phone, role, gender, page, pageSize)

    for (let i = 0; i < list.length; i++) {
      let element = list[i];
      const roleId = await getUserRole(element.id)
      const rolename = await getRoleName(roleId)

      list[i].dataValues = {
        ...list[i].dataValues,
        role: rolename,
      }
    }

    ctx.body = {
      list, total
    }

  }
  // 更新用户信息
  async updateUser(ctx) {
    const { id, username, phone, gender } = ctx.request.body
    const res = await updateuser(id, username, phone, gender)
    if (res) {
      ctx.body = {
        code: 1,
        message: "更新用户信息成功"
      }
    } else {
      ctx.body = {
        code: 0,
        message: "更新用户信息失败"
      }
    }
  }
  // 删除用户
  async deleteUser(ctx) {
    const { id } = ctx.request.body
    const res = await deleteuser(id)
    // console.log(res);
    if (res) {
      ctx.body = {
        code: 1,
        message: "删除用户成功"
      }
    } else {
      ctx.body = {
        code: 0,
        message: "删除用户失败"
      }
    }
  }

  // 审核用户通过
  async examineUser(ctx) {
    const { id, verify } = ctx.request.body
    try {
      const res = await examine(id, verify)
      if (res) {
        ctx.body = {
          code: 1,
          message: "审核用户成功",
          data: res
        }
      } else {
        ctx.body = {
          code: 0,
          message: "审核用户失败"
        }
      }
    } catch (error) {
      console.error(error);
    }
  }
}

module.exports = new UserController()