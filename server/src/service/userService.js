const User = require('../models/sys_user')
const UserRole = require('../models/sys_user_roles')
const RoleMenus = require('../models/sys_role_menus')
const sequelize = require('../db/seq')

class UserService {
  // 注册用户
  async createUser(username, password, phone, gender, email, verify, role) {
    const res = await User.create({ username, password, phone, gender, email, verify })
    const user_id = res.id;

    await UserRole.create({ user_id, role })
    return res.dataValues
  }

  // 查找用户信息
  async getUserInfo({ id, username, password, verify }) {
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

  // 通过userId获取roleId
  async getUserRole(id) {
    const { roles_id } = await UserRole.findOne({
      where: { user_id: id }
    })

    return roles_id
  }

  // 获取用户拥有的菜单
  async getUserMenu(id) {
    const res = await sequelize.query("SELECT id,name,parentId,url,icon FROM sys_menus WHERE id IN (SELECT menu_id FROM sys_role_menus WHERE role_id=(SELECT roles_id FROM sys_user_roles WHERE user_id=" + id + "))");

    let list = []
    let res0 = res[0]
    for (let i in res0) {
      if (res0[i].parentId === 0) {
        list.push({
          "name": res0[i].name,
          "url": res0[i].url,
          "id": res0[i].id,
          "icon": res0[i].icon,
          children: []
        })
      }
    }

    for (let i in list) {
      for (let j of res[0]) {
        // console.log(j.parentId);
        // console.log(list[i].id);
        if (j.parentId === list[i].id) {
          // console.log(j);
          list[i].children.push({
            "name": j.name,
            "url": j.url,
            "id": j.id,
          })
        }
      }
    }

    return list
  }

  // 修改密码
  async updatePassword(id, password, new_password) {

    const res = await User.update({ password: new_password }, {
      where: {
        id
      }
    });

    return res[0] > 0 ? true : false

  }
}

module.exports = new UserService()