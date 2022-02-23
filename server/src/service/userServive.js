const User = require('../models/sys_user')
const UserRole = require('../models/sys_user_roles')

class UserService {
  // 注册用户
  async createUser(username, password, phone, gender, email, verify) {
    const res = await User.create({ username, password, phone, gender, email, verify })
    const user_id = res.id;
    const roles_id = 2;
    // console.log(user_id);
    await UserRole.create({ user_id, roles_id })
    // const users = await User.findAll();
    // console.log(res.id);
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