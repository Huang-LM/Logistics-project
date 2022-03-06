const { getListRole, updateRole, addrole, deleterole, getmenus, getmenu, deleterolemenu, updaterolemenu } = require('../service/roleService')
const { } = require('../constant/errType')

class RoleController {
  // 查询角色列表
  async getListRole(ctx, next) {
    const pageNum = 1, pageSize = 20
    const res = await getListRole(pageNum, pageSize)
    // console.log(res);

    ctx.body = {
      code: 1,
      status: 200,
      message: "查询用户列表成功",
      data: res
    }
  }

  // 查询所有侧边栏
  async getMenus(ctx, next) {
    // const pageNum = 1, pageSize = 20
    const res = await getmenus()
    // console.log(res);

    ctx.body = {
      code: 1,
      status: 200,
      message: "查询所有侧边栏成功",
      data: res
    }
  }

  // 查询角色所拥有的菜单
  async getMenu(ctx, next) {
    // const pageNum = 1, pageSize = 20
    const { role_id } = ctx.request.body
    const res = await getmenu(role_id)
    // console.log(res);

    ctx.body = {
      code: 1,
      status: 200,
      message: "查询角色的菜单成功",
      data: res
    }
  }

  // 更新用户的角色
  async updateUserRole(ctx) {
    const { user_id, new_role } = ctx.request.body
    const res = await updateRole(user_id, new_role)
    if (res) {
      ctx.body = {
        code: 1,
        message: "更新角色成功"
      }
    } else {
      ctx.body = {
        code: 0,
        message: "更新角色失败"
      }
    }
  }

  // 更新用户的角色
  async updateRoleMenu(ctx) {
    const { role_id, menu_id } = ctx.request.body
    // 删除原来的，再添加新的
    await deleterolemenu(role_id)
    const res = await updaterolemenu(menu_id, role_id)

    if (res) {
      ctx.body = {
        code: 1,
        message: "更新角色成功"
      }
    } else {
      ctx.body = {
        code: 0,
        message: "更新角色失败"
      }
    }
  }

  // 新增角色
  async add(ctx) {
    const { name, note } = ctx.request.body

    const res = await addrole(name, note)
    if (res) {
      ctx.body = {
        code: 1,
        message: "新增角色成功"
      }
    } else {
      ctx.body = {
        code: 0,
        message: "新增角色失败"
      }
    }
  }
  // 删除角色
  async deleteRole(ctx) {
    const { id } = ctx.request.body
    try {
      await deleterolemenu(id)
      const res = await deleterole(id)
      if (res) {
        ctx.body = {
          code: 1,
          message: "删除角色成功"
        }
      } else {
        ctx.body = {
          code: 0,
          message: "删除角色失败"
        }
      }
    } catch (error) {
      console.error(error);
    }
  }
}

module.exports = new RoleController()