const {
  DataTypes, Sequelize
} = require('sequelize');

var moment = require('moment');
const sequelize = require('../db/seq')

// module.exports = sequelize => {
const attributes = {
  id: {
    type: DataTypes.INTEGER,
    allowNull: false,
    defaultValue: null,
    primaryKey: true,
    autoIncrement: true,
    comment: "主键id",
    field: "id"
  },
  role_id: {
    type: DataTypes.INTEGER,
    allowNull: false,
    defaultValue: null,
    primaryKey: false,
    autoIncrement: false,
    comment: "角色id",
    field: "role_id"
  },
  menu_id: {
    type: DataTypes.INTEGER,
    allowNull: false,
    defaultValue: null,
    primaryKey: false,
    autoIncrement: false,
    comment: "菜单id",
    field: "menu_id"
  },
  createTime: {
    type: DataTypes.DATE,
    allowNull: false,
    defaultValue: Sequelize.literal('CURRENT_TIMESTAMP'),
    get() {
      return moment(this.getDataValue('createTime')).format('YYYY-MM-DD HH:mm:ss');
    },
    primaryKey: false,
    autoIncrement: false,
    comment: null,
    field: "createTime"
  },
  updateTime: {
    type: DataTypes.DATE,
    allowNull: false,
    defaultValue: Sequelize.literal('CURRENT_TIMESTAMP'),
    get() {
      return moment(this.getDataValue('updateTime')).format('YYYY-MM-DD HH:mm:ss');
    },
    primaryKey: false,
    autoIncrement: false,
    comment: null,
    field: "updateTime"
  }
};
const options = {
  tableName: "sys_role_menus",
  createdAt: "createTime",
  updatedAt: "updateTime",
  comment: "",
  indexes: []
};
// const SysRoleMenusModel = sequelize.define("sys_role_menus_model", attributes, options);
// return SysRoleMenusModel;
// };
const RoleMenus = sequelize.define("sys_role_menus_model", attributes, options);
//   return SysUserRolesModel;
// };
module.exports = RoleMenus