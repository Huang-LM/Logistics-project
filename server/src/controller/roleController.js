const { getListRole } = require('../service/roleService')
const { } = require('../constant/errType')

class RoleController {
  // 查询角色列表
  async getListRole(ctx, next) {
    const pageNum = 1, pageSize = 20
    const res = await getListRole(pageNum, pageSize)
    // console.log(res);

    ctx.body = {
      code: 1,
      status: 200,
      message: "查询用户列表成功",
      data: res
    }
  }
}

module.exports = new RoleController()