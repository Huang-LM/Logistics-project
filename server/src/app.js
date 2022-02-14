const Koa = require('koa')
const app = new Koa()

const router = require('./router')

const { APP_PORT } = require('./config/config.default')



// 启动路由
app.use(router.routes(), router.allowedMethods())

app.listen(APP_PORT, () => {
  console.log(`server is running on http://localhost:${APP_PORT}`)
})