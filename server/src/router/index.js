const Router = require('koa-router')
const router = new Router()

// app.use((ctx, next) =>{
//   ctx.body = 'hello world'
// })

// router.get('/', async (req, res) => {
//   res.send('GET request to the homepage')
// })

router.get('/', async (ctx) => {
  ctx.body = 'sss'
})

router.get('/list', async (ctx) => {
  ctx.body = 'awdwd'
})

module.exports = router