const Router = require('koa-router')
// 前缀统一为users
const { register, login } = require('../controller/userController')
const { userValidator, verifyUser, crpytPassword } = require('../middleware/userMiddleWare')

const router = new Router({ prefix: '/users' })

// 注册接口
router.post('/register', userValidator, verifyUser, crpytPassword, register)

// 登录接口
router.post('/login', login)

module.exports = router
