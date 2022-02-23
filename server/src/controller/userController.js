const { createUser, getUerInfo } = require('../service/userServive')

class UserController {
  // 注册用户
  async register(ctx, next) {
    // 1. 获取数据
    const { username, password, phone, gender, email, verify } = ctx.request.body
    
    if (!username || !password) {
      console.error('用户名或密码为空', ctx.request.body)
      ctx.status = 400
      ctx.body = {
        code: '10001',
        message: '用户名或密码为空',
        result: '',
      }
      return
    }
    // 合理性
    if (getUerInfo({ username })) {
      ctx.status = 409
      ctx.body = {
        code: '10002',
        message: '用户已经存在',
        result: '',
      }
      return
    }

    // 2. 操作数据库
    const res = await createUser(username, password, phone, gender, email, verify)
    // console.log(res)
    // 3. 返回结果
    ctx.body = ctx.request.body
  }

  async login(ctx, next) {
    ctx.body = '登录成功'
  }
}

module.exports = new UserController()