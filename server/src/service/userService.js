const User = require('../models/sys_user')
const UserRole = require('../models/sys_user_roles')
const RoleMenus = require('../models/sys_role_menus')
const sequelize = require('../db/seq')
const Role = require('../models/sys_role')
const {
  Op
} = require('sequelize');

class UserService {
  // 注册用户
  async createUser(username, password, phone, gender, email, verify, role) {
    const res = await User.create({ username, password, phone, gender, email, verify })
    const roleId = await Role.findOne({ attributes: ['id'], where: { name: role } })
    const user_id = res.id;
    // console.log(roleId.dataValues.id);
    const roles_id = roleId.dataValues.id

    await UserRole.create({ user_id, roles_id })
    return res.dataValues
  }

  // 查找用户信息
  async getUserInfo({ id, username, email, password, verify }) {
    const whereOpt = {}

    id && Object.assign(whereOpt, { id })
    username && Object.assign(whereOpt, { username })
    password && Object.assign(whereOpt, { password })
    verify && Object.assign(whereOpt, { verify })
    email && Object.assign(whereOpt, { email })

    const res = await User.findOne({
      attributes: ['id', 'username', 'password', 'verify', 'phone', 'gender'],
      where: whereOpt,
    })

    return res ? res.dataValues : null
  }

  // 通过userId获取roleId
  async getUserRole(id) {
    const { roles_id } = await UserRole.findOne({
      attributes: ['roles_id'],
      where: { user_id: id }
    })

    // console.log(roles_id);
    return roles_id
  }

  // 通过roleId获取roleName
  async getRoleName(id) {
    const { name } = await Role.findOne({
      attributes: ['name'],
      where: { id: id }
    })

    return name
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
      where: { id }
    });
    return res[0] > 0 ? true : false
  }

  // 找回密码
  async updateFindPassword(email, password) {
    const res = await User.update({ password: password }, {
      where: { email }
    });
    return res[0] > 0 ? true : false
  }

  // 更新用户信息
  async updateuser(id, username, phone, gender) {
    const res = await User.update({ username: username, phone: phone, gender: gender },
      { where: { id } }
    )
    return res[0] > 0 ? true : false
  }

  // 删除用户
  async deleteuser(id) {
    const res = await User.destroy({ where: { id: id } })
    // console.log(res);
    return res > 0 ? true : false
  }

  // 获取角色列表
  async getUserList(
    id,
    username,
    phone,
    role,
    gender,
    page,
    pageSize) {

    const whereOpt = {}

    id && Object.assign(whereOpt, { id })
    username && Object.assign(whereOpt, { username })
    phone && Object.assign(whereOpt, { phone })
    // role && Object.assign(whereOpt, { role })
    gender && Object.assign(whereOpt, { gender })
    const offset = page * pageSize
    if (role) {
      const res = await UserRole.findAll({
        attributes: ['user_id'], where: {
          roles_id: role
        }
      })
      // console.log(res);
      let uid = []
      for (let i of res) {
        uid.push(i.dataValues.user_id)
      }
      // console.log(uid);
      whereOpt.id = {
        [Op.or]: uid
      }
      // console.log(whereOpt);
      const { count, rows } = await User.findAndCountAll({
        attributes: { exclude: ['password'] },
        where: whereOpt,
        offset: offset,
        limit: pageSize
      });
      console.log(rows);
      return {
        list: rows,
        total: count
      }
    } else {
      const { count, rows } = await User.findAndCountAll({
        attributes: { exclude: ['password'] },
        where: whereOpt,
        offset: offset,
        limit: pageSize
      });
      return {
        list: rows,
        total: count
      }
    }

  }

  async examine(id, verify) {
    const res = await User.update({ verify: verify }, { where: { id: id } })
    // console.log(res);
    return res[0] > 0 ? true : false
  }

}


module.exports = new UserService()