const Logistics = require("../models/sys_logistics")
const UserLogistics = require("../models/sys_user_logistics")

const {
  Op
} = require('sequelize');
const LogisticsState = require("../models/sys_logistics_state");

class LogisticsService {

  async add(
    mailing_name,
    mailing_phone,
    mailing_address,
    shipping_name,
    shipping_phone,
    shipping_address,
    mailing_type,
    logistics_way,
    note,
    user_id) {

    const res = await Logistics.create({
      mailing_name,
      mailing_phone,
      mailing_address,
      shipping_name,
      shipping_phone,
      shipping_address,
      mailing_type,
      logistics_way,
      note,
      user_id,
      verify: 0
    })

    await UserLogistics.create({
      logistics_id: res.dataValues.id,
      user_id: user_id
    })

    await LogisticsState.create({
      logistics_id: res.dataValues.id,
      state_id: 1
    })

    return res.dataValues
  }

  async find(user_id, logistics_number) {
    let id = null
    let idss = []
    if (user_id) {
      const ids = await UserLogistics.findAll({
        attributes: ['logistics_id'],
        where: { user_id }
      })
      for (let i = 0; i < ids.length; i++) {
        const element = ids[i];
        idss.push(element.dataValues.logistics_id)
      }
      id = {
        [Op.or]: idss
      }
    }
    const res = await Logistics.findAll({
      where: {
        id,
        logistics_number
      }
    })

    console.log(res);
    return res
  }

  async findState(logistics_number) {
    const res = await LogisticsState.findAll({
      where: { logistics_number }
    })
    // 之后还要把res的stateid提取出来查询返回

    return res
  }


}

module.exports = new LogisticsService()
