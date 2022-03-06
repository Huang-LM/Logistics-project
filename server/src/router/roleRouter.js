const Router = require('koa-router')

const { getListRole, updateUserRole, add, deleteRole, getMenus, getMenu, updateRoleMenu } = require('../controller/roleController')
const { auth } = require('../middleware/authMiddleWare')
// const { userValidator, verifyUser, crpytPassword, verifyLogin, verifyPassword } = require('../middleware/userMiddleWare')

const router = new Router({ prefix: '/role' })

// 注册接口
// router.post('/register', userValidator, verifyUser, crpytPassword, register)

// 获取角色列表
router.get('/listRole', auth, getListRole)

// 查询所有侧边栏
router.get('/menus', auth, getMenus)

// 查询角色所拥有的菜单
router.post('/menu', auth, getMenu)

// 更新角色权限
router.post('/updateRole', auth, updateUserRole)

// 更新角色的菜单
router.post('/updateRoleMenu', auth, updateRoleMenu)

// 新增角色
router.post('/add', auth, add)

// 删除角色
router.post('/delete', auth, deleteRole)


module.exports = router
