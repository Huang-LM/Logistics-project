const jwt = require('jsonwebtoken')

const { JWT_SECRET } = process.env

const { tokenExpiredError, invalidToken } = require('../constant/errType')

const auth = async (ctx, next) => {
  // console.log(ctx.request.header.token);
  let authorization = 0;
  if (ctx.request.header.token) {
    authorization = ctx.request.header.token
  } else {
    authorization = ctx.request.header.authorization
  }

  // console.log(authorization)
  const token = authorization.replace('Bearer ', '')

  try {
    const user = jwt.verify(token, JWT_SECRET)
    ctx.state.user = user
  } catch (err) {
    switch (err.name) {
      case 'TokenExpiredError':
        console.error('token已过期', err)
        return ctx.app.emit('error', tokenExpiredError, ctx)
      case 'JsonWebTokenError':
        console.error('无效的token', err)
        return ctx.app.emit('error', invalidToken, ctx)
    }
  }

  await next()
}

module.exports = {
  auth,
}