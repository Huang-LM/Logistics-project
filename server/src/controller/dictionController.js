const { find, add, deleteD } = require("../service/dictionService")

class DictionController {

  // 查找pid为0
  async findDic(ctx) {
    const { parentId, name } = ctx.request.body
    try {
      const res = await find(parentId, name)
      if (res) {
        ctx.body = {
          code: 1,
          message: "查找字典数据成功",
          data: res
        }
      } else {
        ctx.body = {
          code: 0,
          message: "查找字典数据失败"
        }
      }
    } catch (error) {
      console.error(error);
    }
  }

  // 新增字典数据
  async addDic(ctx) {
    const { name, parentId } = ctx.request.body
    try {
      const res = await add(name, parentId)
      if (res) {
        ctx.body = {
          code: 1,
          message: "新增成功",
          data: res
        }
      } else {
        ctx.body = {
          code: 0,
          message: "新增失败"
        }
      }
    } catch (error) {
      console.error(error);
    }
  }

  // 删除字典数据
  async deleteDic(ctx) {
    const { id } = ctx.request.body
    try {
      const res = await deleteD(id)
      if (res) {
        ctx.body = {
          code: 1,
          message: "删除成功",
          data: res
        }
      } else {
        ctx.body = {
          code: 0,
          message: "删除失败"
        }
      }
    } catch (error) {
      console.error(error);
    }
  }
}


module.exports = new DictionController()