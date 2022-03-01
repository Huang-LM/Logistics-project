const User = require('../models/sys_user')
const UserRole = require('../models/sys_user_roles')
const Role = require('../models/sys_role')

class RoleService {
  // 查找角色列表
  async getListRole(pageNum, pageSize) {
    try {
      const offset = (pageNum - 1) * pageSize
      const row = await Role.findAll({ offset: offset, limit: pageSize * 1 })

      return row
    } catch (error) {
      console.log(error);
    }


  }
  // // 查找角色
  // async getUerInfo({ id, username, password, verify }) {
  //   const whereOpt = {}

  //   id && Object.assign(whereOpt, { id })
  //   username && Object.assign(whereOpt, { username })
  //   password && Object.assign(whereOpt, { password })
  //   verify && Object.assign(whereOpt, { verify })

  //   const res = await User.findOne({
  //     attributes: ['id', 'username', 'password', 'verify'],
  //     where: whereOpt,
  //   })

  //   return res ? res.dataValues : null
  // }
}

module.exports = new RoleService()