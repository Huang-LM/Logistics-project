module.exports = (err, ctx) => {
  let status = 500
  switch (err.code) {
    case '101':
      status = 400
      break
    case '401':
      status = 401
      break
    case '102':
      status = 409
      break
    case '201':
    case '202':
      status = 401
      break
    default:
      status = 500
  }
  ctx.status = status
  ctx.body = err
  console.log(err)
}