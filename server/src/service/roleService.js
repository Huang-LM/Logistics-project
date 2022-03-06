const UserRole = require('../models/sys_user_roles')
const Role = require('../models/sys_role')
const Menus = require('../models/sys_menus')
const RoleMenus = require('../models/sys_role_menus')

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

  // 查找所有侧边栏
  async getmenus() {
    try {
      const res = await Menus.findAll()
      // console.log(res[0].dataValues.name);

      let list = []
      for (let i in res) {
        if (res[i].dataValues.parentId === 0) {
          list.push({
            "name": res[i].dataValues.name,
            "url": res[i].dataValues.url,
            "id": res[i].dataValues.id,
            "icon": res[i].dataValues.icon,
            children: []
          })
        }
      }
      // console.log(list);

      for (let i in list) {
        for (let j of res) {
          if (j.dataValues.parentId === list[i].id) {
            list[i].children.push({
              "name": j.dataValues.name,
              "url": j.dataValues.url,
              "id": j.dataValues.id,
            })
          }
        }
      }
      return list
    } catch (error) {
      console.log(error);
    }
  }

  // 查询角色所拥有的菜单
  async getmenu(role_id) {
    const res = await RoleMenus.findAll({
      where: {
        role_id: role_id
      }
    })

    let list = []
    for (let i in res) {
      if (res[i].dataValues.menu_id < 100) {
        list.push({
          "id": res[i].dataValues.menu_id,
          children: []
        })
      }
    }
    for (let i in list) {
      for (let j of res) {
        if (list[i].id !== j.dataValues.menu_id) {
          let x = j.dataValues.menu_id.toString()
          if (list[i].id == x.substr(0, 2)) {
            list[i].children.push({
              "id": j.dataValues.menu_id,
            })
          }
        }
      }
    }
    return list
  }

  // 更新用户角色
  async updateRole(id, role) {
    try {
      const res1 = await Role.findOne({ attributes: ['id'], where: { name: role } })

      const roleId = res1.dataValues.id
      const res = await UserRole.update({ roles_id: roleId },
        { where: { user_id: id } }
      )
      return res[0] > 0 ? true : false
    } catch (error) {
      console.log(error);
    }
  }

  // 更新角色信息
  async updaterolemenu(menuId, roleId) {
    try {
      for (let i of menuId) {
        await RoleMenus.create({ role_id: roleId, menu_id: i })
      }
      return true
    } catch (error) {
      console.log(error);
    }
  }

  // 删除角色的菜单
  async deleterolemenu(roleId) {
    try {
      const res = await RoleMenus.destroy({ where: { role_id: roleId } })
      // console.log(res);
      return res
    } catch (error) {
      console.log(error);
    }
  }

  // 新增角色
  async addrole(name, note) {
    try {
      const res = await Role.create({ name, note })

      return res.dataValues
    } catch (error) {
      console.error(error);
    }
  }

  // 删除角色
  async deleterole(id) {
    try {
      const res = await Role.destroy({ where: { id: id } })
      return res > 0 ? true : false
    } catch (error) {
      console.error(error);
    }
  }

}

module.exports = new RoleService()