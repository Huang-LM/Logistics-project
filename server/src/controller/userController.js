const { createUser, getUerInfo } = require('../service/userServive')
const { userRegisterError } = require('../constant/errType')

class UserController {
  // 注册用户
  async register(ctx, next) {
    const { username, password, phone, gender, email, verify } = ctx.request.body

    try {
      const res = await createUser(username, password, phone, gender, email, verify)
      // 还需要用户的身份
      ctx.body = {
        code: 0,
        message: "用户注册成功",
        result: {
          id: res.id,
          username: res.username
        }
      }
    } catch (err) {
      console.log(err)
      ctx.app.emit('error', userRegisterError, ctx)
    }
  }

  async login(ctx, next) {
    ctx.body = '登录成功'
  }
}

module.exports = new UserController()