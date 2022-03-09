const Router = require('koa-router')
const { sendLogi, getLogi, getState, getListLogi, updateLogi, deleteLogi, allState, findState } = require('../controller/logisticsController')

const { auth } = require('../middleware/authMiddleWare')
const router = new Router({ prefix: '/logisticsInfo' })

// 申请寄件
router.post('/send', auth, sendLogi)

// 通过userid或物流编号查询信息
router.post('/getLogistics', auth, getLogi)

// 获取物流状态
router.post('/state', auth, getState)

// 获取所有物流信息
router.post('/listLogistics', auth, getListLogi)

// 寄件审核
router.post('/updateLogistics', auth, updateLogi)

// 删除物流信息
router.post('/delete', auth, deleteLogi)

// 获取所有物流状态
router.get('/allState', auth, allState)


module.exports = router