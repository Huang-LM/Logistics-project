const Router = require('koa-router')

const { getListRole } = require('../controller/roleController')
const { auth } = require('../middleware/authMiddleWare')
// const { userValidator, verifyUser, crpytPassword, verifyLogin, verifyPassword } = require('../middleware/userMiddleWare')

const router = new Router({ prefix: '/role' })

// 注册接口
// router.post('/register', userValidator, verifyUser, crpytPassword, register)

// 获取角色列表
router.get('/listRole', auth, getListRole)


module.exports = router
