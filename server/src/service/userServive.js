const User = require('../models/sys_user')

class UserService {
  // 注册用户
  async createUser(username, password, phone, gender, email, verify) {
    const res = await User.create({username, password, phone, gender, email, verify})
    // const users = await User.findAll();
    // console.log(res);
    return res.dataValues
  }

  // 查找用户信息
  async getUerInfo({ id, username, password, verify }) {
    const whereOpt = {}

    id && Object.assign(whereOpt, { id })
    username && Object.assign(whereOpt, { username })
    password && Object.assign(whereOpt, { password })
    verify && Object.assign(whereOpt, { verify })

    const res = await User.findOne({
      attributes: ['id', 'username', 'password', 'verify'],
      where: whereOpt,
    })

    return res ? res.dataValues : null
  }
}

module.exports = new UserService()