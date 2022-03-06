const Router = require('koa-router')
const { findId, addDic, deleteDic } = require('../controller/dictionController')

const router = new Router({ prefix: '/dictionaries' })

const { auth } = require('../middleware/authMiddleWare')

// 查找某id下的子id
router.post('/find', auth, findId)

// 新增字典数据
router.post('/add', auth, addDic)

// 删除字典数据
router.post('/delete', auth, deleteDic)


module.exports = router