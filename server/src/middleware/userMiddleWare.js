const bcrypt = require('bcryptjs')
const { getUserInfo } = require('../service/userService')
const {
  userFormateError,
  userAlreadyExited,
  userRegisterError,
  userDoesNotExist,
  userLoginError,
  invalidPassword,
  emailAlreadyExited,
} = require('../constant/errType')

// 验证用户名或密码是否为空
const userValidator = async (ctx, next) => {
  const { username, password } = ctx.request.body
  // 合法性
  if (!username || !password) {
    console.error('用户名或密码为空', ctx.request.body)
    ctx.app.emit('error', userFormateError, ctx)
    return
  }

  await next()
}

// 验证用户是否已经存在
const verifyUser = async (ctx, next) => {
  const { username } = ctx.request.body

  try {
    const res = await getUserInfo({ username })

    if (res) {
      console.error('用户名已经存在', { username })
      ctx.app.emit('error', userAlreadyExited, ctx)
      return
    }
  } catch (err) {
    console.error('获取用户信息错误', err)
    ctx.app.emit('error', userRegisterError, ctx)
    return
  }

  await next()
}
// 验证邮箱是否已经存在
const verifyEmail = async (ctx, next) => {
  const { email } = ctx.request.body

  try {
    const res = await getUserInfo({ email })

    // console.log(res);
    if (res) {
      console.error('邮箱已被使用', { email })
      ctx.app.emit('error', emailAlreadyExited, ctx)
      return
    }
  } catch (err) {
    console.error('获取用户信息错误', err)
    ctx.app.emit('error', userRegisterError, ctx)
    return
  }

  await next()
}

// 加密密码
const crpytPassword = async (ctx, next) => {
  const { password, new_password } = ctx.request.body

  const salt = bcrypt.genSaltSync(10)
  // hash保存的是 密文
  const hash = bcrypt.hashSync(password, salt)

  ctx.request.body.password = hash

  if (new_password) {
    const hash2 = bcrypt.hashSync(new_password, salt)
    ctx.request.body.new_password = hash2
  }

  await next()
}

// 验证登录信息
const verifyLogin = async (ctx, next) => {

  const { username, password } = ctx.request.body

  try {
    // 1. 判断用户是否存在(不存在:报错)
    const res = await getUserInfo({ username })
    // console.log(res);

    if (!res) {
      console.error('用户名不存在', { username })
      ctx.app.emit('error', userDoesNotExist, ctx)
      return
    }

    // 2. 密码是否匹配(不匹配: 报错)
    if (!bcrypt.compareSync(password, res.password)) {
      ctx.app.emit('error', invalidPassword, ctx)
      return
    }
  } catch (err) {
    console.error(err)
    return ctx.app.emit('error', userLoginError, ctx)
  }

  await next()
}

// 验证旧密码是否正确
const verifyPassword = async (ctx, next) => {

  const { id, email, password } = ctx.request.body

  try {
    if (email) {
      console.log(email);
      const res = await getUserInfo({ email })
      console.log(res);

      // 密码是否匹配
      if (!bcrypt.compareSync(password, res.password)) {
        ctx.app.emit('error', invalidPassword, ctx)
        return
      }
    } else {
      const res = await getUserInfo({ id })

      // 密码是否匹配
      if (!bcrypt.compareSync(password, res.password)) {
        ctx.app.emit('error', invalidPassword, ctx)
        return
      }
    }

  } catch (err) {
    console.error(err)
    return ctx.app.emit('error', invalidPassword, ctx)
  }

  await next()
}


module.exports = {
  userValidator,
  verifyUser,
  crpytPassword,
  verifyLogin,
  verifyPassword,
  verifyEmail
}