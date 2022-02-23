const bcrypt = require('bcryptjs')
const { getUerInfo } = require('../service/userServive')
const { userFormateError, userAlreadyExited } = require('../constant/errType')

// 验证用户名或密码是否为空
const userValidator = async (ctx, next) => {
  const { username, password } = ctx.request.body
  // 合法性
  if (!username || !password) {
    // console.error('用户名或密码为空', ctx.request.body)
    ctx.app.emit('error', userFormateError, ctx)
    return
  }

  await next()
}

// 验证用户是否已经存在
const verifyUser = async (ctx, next) => {
  const { username } = ctx.request.body

  if (await getUerInfo({ username })) {
    ctx.app.emit('error', userAlreadyExited, ctx)
    return
  }

  await next()
}

// 加密密码
const crpytPassword = async (ctx, next) => {
  const { password } = ctx.request.body

  const salt = bcrypt.genSaltSync(10)
  // hash保存的是 密文
  const hash = bcrypt.hashSync(password, salt)

  ctx.request.body.password = hash

  await next()
}

module.exports = {
  userValidator,
  verifyUser,
  crpytPassword
}