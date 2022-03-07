
const { add, find, findState } = require("../service/logisticsService")

class LogisticsController {
  async sendLogi(ctx) {
    const { mailing_name,
      mailing_phone,
      mailing_address,
      shipping_name,
      shipping_phone,
      shipping_address,
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
      const { logistics_number } = ctx.request.body

      const res = await findState(logistics_number)
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