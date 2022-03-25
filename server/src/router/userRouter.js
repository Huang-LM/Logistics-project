const Router = require('koa-router')
// 前缀统一为users
const { register, login, updatapw, getMenu, getList, mailCode, checkCode, findPassword, getUser, updateUser, deleteUser, add, examineUser, upload, announceList, addAnnounce } = require('../controller/userController')
const { auth } = require('../middleware/authMiddleWare')
const { userValidator, verifyUser, crpytPassword, verifyLogin, verifyPassword, verifyEmail } = require('../middleware/userMiddleWare')

const router = new Router({ prefix: '/user' })

// 注册接口
router.post('/register', userValidator, verifyUser, crpytPassword, register)

// 管理员的注册接口
router.post('/add', userValidator, verifyUser, crpytPassword, add)

// 注册邮箱验证码
router.post('/code', verifyEmail, mailCode)

// 找回密码时的发送邮箱验证码
router.post('/code2', mailCode)

// 找回密码时的验证
router.post('/checkCode', checkCode)

// 找回密码
router.post('/findPassword', crpytPassword, findPassword)

// 登录接口
router.post('/login', userValidator, verifyLogin, login)

// 更新密码接口
router.post('/updatepw', auth, verifyPassword, crpytPassword, updatapw)

// 更新用户信息
router.post('/update', auth, updateUser)

// 删除用户
router.post('/delete', auth, deleteUser)

// 获取角色拥有的菜单
router.post('/menu', auth, getMenu)

// 查询用户列表
router.post('/list', auth, getList)
router.post('/select', auth, getList)

// 通过user_id获取用户信息
router.post('/get', auth, getUser)

// 审核用户是否可以登录
router.post('/examine', auth, examineUser)

// 公告上传
router.post('/upload', upload)

// 公告列表获取
router.get('/announceList', auth, announceList)

// 发布公告
router.post('/addAnnounce', auth, addAnnounce)

module.exports = router
