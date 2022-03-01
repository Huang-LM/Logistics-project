const jwt = require('jsonwebtoken')
const { createUser, getUserInfo, getUserRole, updatePassword, getUserMenu } = require('../service/userService')
const { userRegisterError, userLoginError, userNotApproved } = require('../constant/errType')
const { JWT_SECRET } = process.env

class UserController {
  // 注册用户
  async register(ctx, next) {
    const { username, password, phone, gender, email, verify, role } = ctx.request.body

    try {
      const res = await createUser(username, password, phone, gender, email, verify, role)
      ctx.body = {
        code: 1,
        status: 200,
        data: {
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

  // 登录操作
  async login(ctx, next) {
    const { username } = ctx.request.body

    try {
      const { password, ...res } = await getUserInfo({ username })
      // console.log(res);

      // 未通过审核无法登录
      if (!res.verify) {
        ctx.app.emit('error', userNotApproved, ctx)
        return
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
    const id = ctx.state.user.id
    const password = ctx.request.body.password
    const new_password = ctx.request.body.new_password

    if (await updatePassword(id, password, new_password)) {
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

  // 获取该用户菜单
  async getMenu(ctx) {
    const id = ctx.request.body.id
    // console.log(ctx.request.body.id);
    const res = await getUserMenu(id)
    // console.log(res);
    ctx.body = {
      data: res
    }
  }
}

module.exports = new UserController()