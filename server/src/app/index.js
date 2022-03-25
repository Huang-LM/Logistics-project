const path = require('path')

const Koa = require('koa')
const KoaBody = require('koa-body')
const KoaStatic = require('koa-static')

const router = require('../router')

const errHandler = require('./errHandler')

const app = new Koa()

app.use(KoaBody(
  {
    multipart: true,
    formidable: {
      uploadDir: path.join(__dirname, '../upload'),
      keepExtensions: true
    }
  }
))
app.use(KoaStatic(path.join(__dirname, '../upload')))
app.use(router.routes()).use(router.allowedMethods())

// 统一的错误处理
app.on('error', errHandler)

module.exports = app