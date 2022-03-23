
const { add, find, findState, findListLogi, updatelogi, deletelogi, getAllState, addstate } = require("../service/logisticsService")

class LogisticsController {
  async sendLogi(ctx) {
    const { mailing_name,
      mailing_phone,
      mailing_address,
      shipping_name,
      shipping_phone,
      shipping_address,
      shipping_time,
      mailing_type,
      logistics_way,
      note,
      user_id } = ctx.request.body
    try {
      const res = await add(mailing_name,
        mailing_phone,
        mailing_address,
        shipping_name,
        shipping_phone,
        shipping_address,
        shipping_time,
        mailing_type,
        logistics_way,
        note,
        user_id)


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

  async addState(ctx) {
    const { state_id, logistics_id } = ctx.request.body
    try {
      const res = await addstate(state_id, logistics_id)

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

  async getLogi(ctx) {
    try {
      const { user_id, logistics_number } = ctx.request.body

      const res = await find(user_id, logistics_number)
      if (res) {
        ctx.body = {
          code: 1,
          message: "查询成功",
          data: res
        }
      } else {
        ctx.body = {
          code: 0,
          message: "查询失败"
        }
      }
    } catch (error) {
      console.error(error);
    }

  }

  async getState(ctx) {
    try {
      const { id } = ctx.request.body
      // console.log(id);
      const res = await findState(id)
      if (res) {
        ctx.body = {
          code: 1,
          message: "查询成功",
          data: res
        }
      } else {
        ctx.body = {
          code: 0,
          message: "查询失败"
        }
      }
    } catch (error) {
      console.error(error);
    }
  }

  async getListLogi(ctx) {
    try {
      const { mailing_phone, logistics_number, logistics_way_number, page, pageSize } = ctx.request.body

      const res = await findListLogi(mailing_phone, logistics_number, logistics_way_number, page, pageSize)
      if (res) {
        ctx.body = {
          code: 1,
          message: "查询成功",
          data: res
        }
      } else {
        ctx.body = {
          code: 0,
          message: "查询失败"
        }
      }
    } catch (error) {
      console.error(error);
    }
  }

  async updateLogi(ctx) {
    try {
      const { id, verify, logistics_way, logistics_way_number, shipping_time } = ctx.request.body

      const res = await updatelogi(id, verify, logistics_way, logistics_way_number, shipping_time)

      if (res) {
        ctx.body = {
          code: 1,
          message: "更新成功",
        }
      } else {
        ctx.body = {
          code: 0,
          message: "更新失败"
        }
      }
    } catch (error) {
      console.error(error);
    }
  }

  async deleteLogi(ctx) {
    try {
      const { id } = ctx.request.body
      // console.log(id);

      const res = await deletelogi(id)
      if (res) {
        ctx.body = {
          code: 1,
          message: "删除成功",
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

  async allState(ctx) {
    try {
      const res = await getAllState()
      if (res) {
        ctx.body = {
          code: 1,
          message: "查询成功",
          data: res
        }
      } else {
        ctx.body = {
          code: 0,
          message: "查询失败"
        }
      }
    } catch (error) {
      console.error(error);
    }
  }

  async findState(ctx) {
    try {
      const { id } = ctx.request.body
      const res = await findState(id)
      if (res) {
        ctx.body = {
          code: 1,
          message: "查询成功",
          data: res
        }
      } else {
        ctx.body = {
          code: 0,
          message: "查询失败"
        }
      }
    } catch (error) {
      console.error(error);
    }
  }

}

module.exports = new LogisticsController()