const Logistics = require("../models/sys_logistics")
const UserLogistics = require("../models/sys_user_logistics")
const LogisticsState = require("../models/sys_logistics_state");
const State = require("../models/sys_state");

const {
  Op
} = require('sequelize');


class LogisticsService {

  async add(
    mailing_name,
    mailing_phone,
    mailing_address,
    shipping_name,
    shipping_phone,
    shipping_address,
    shipping_time,
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
      shipping_time: null,
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

    // await LogisticsState.create({
    //   logistics_id: res.dataValues.id,
    //   state_id: 1
    // })

    return res.dataValues
  }

  async addstate(state_id, logistics_id) {
    const res = await LogisticsState.create({ state_id, logistics_id })
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
    if (!logistics_number) {
      const res = await Logistics.findAll({
        where: {
          id
        }
      })
      return res
    } else {
      const res = await Logistics.findAll({
        where: {
          id,
          logistics_number
        }
      })
      return res
    }
  }

  async findState(id) {
    let state_id = null
    let stateIdss = []
    const stateIds = await LogisticsState.findAll({ attributes: ['state_id'], where: { logistics_id: id } })
    console.log(stateIds);
    if (stateIds.length !== 0) {
      for (let i = 0; i < stateIds.length; i++) {
        const element = stateIds[i];
        stateIdss.push(element.dataValues.state_id)
      }
      state_id = {
        [Op.or]: stateIdss
      }
      console.log(state_id);

      const res = await State.findAll({
        where: {
          id: state_id
        }
      })
      return res
    } else {
      return
    }


  }


  async findListLogi(mailing_phone, logistics_number, page, pageSize) {
    try {

      const whereOpt = {}

      mailing_phone && Object.assign(whereOpt, { mailing_phone })
      logistics_number && Object.assign(whereOpt, { logistics_number })

      const offset = page * pageSize

      const { count, rows } = await Logistics.findAndCountAll({
        where: whereOpt,
        offset: offset,
        limit: pageSize
      });

      // console.log(rows);

      return {
        list: rows,
        total: count
      }
    } catch (error) {
      console.log(error);
    }
  }

  async updatelogi(id, verify, logistics_way, logistics_way_number, shipping_time) {
    const opt = {}
    const lastVerify = await Logistics.findOne({ attributes: ['verify'], where: { id: id } })
    // console.log(lastVerify);

    if (lastVerify.dataValues.verify === 0 && verify === 1) {
      let logistics_number = Math.floor((Math.random() + Math.floor(Math.random() * 9 + 1)) * Math.pow(10, 9))
      // console.log(logistics_number);
      Object.assign(opt, { logistics_number })

      await LogisticsState.create({
        logistics_id: id,
        state_id: 1
      })
    }

    id && Object.assign(opt, { id })
    verify && Object.assign(opt, { verify })
    logistics_way && Object.assign(opt, { logistics_way })
    logistics_way_number && Object.assign(opt, { logistics_way_number })
    if (shipping_time !== 'Invalid date') {
      Object.assign(opt, { shipping_time })
    }

    const res = await Logistics.update(opt, { where: { id } })
    return res
  }

  async deletelogi(id) {
    await LogisticsState.destroy({ where: { logistics_id: id } })
    await UserLogistics.destroy({ where: { logistics_id: id } })
    const res = await Logistics.destroy({ where: { id } })

    return res > 0 ? true : false

  }

  async getAllState() {
    const res = await State.findAll()
    return res
  }

  async getState(id) {
    const res = await LogisticsState.findAll({ where: { logistics_id: id } })
    return res
  }
}

module.exports = new LogisticsService()
