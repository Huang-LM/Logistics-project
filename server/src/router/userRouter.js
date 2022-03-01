const Router = require('koa-router')
// 前缀统一为users
const { register, login, updatapw, getMenu } = require('../controller/userController')
const { auth } = require('../middleware/authMiddleWare')
const { userValidator, verifyUser, crpytPassword, verifyLogin, verifyPassword } = require('../middleware/userMiddleWare')

const router = new Router({ prefix: '/user' })

// 注册接口
router.post('/register', userValidator, verifyUser, crpytPassword, register)

// 登录接口
router.post('/login', userValidator, verifyLogin, login)

router.patch('/updatepw', auth, verifyPassword, crpytPassword, updatapw)

router.post('/menu', getMenu)

module.exports = router
