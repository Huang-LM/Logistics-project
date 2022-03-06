const {
  DataTypes, Sequelize
} = require('sequelize');

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
  user_id: {
    type: DataTypes.INTEGER,
    allowNull: false,
    defaultValue: null,
    primaryKey: false,
    autoIncrement: false,
    comment: "用户id",
    field: "user_id"
  },
  roles_id: {
    type: DataTypes.INTEGER,
    allowNull: false,
    defaultValue: null,
    primaryKey: false,
    autoIncrement: false,
    comment: "角色id",
    field: "roles_id"
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
  tableName: "sys_user_roles",
  timestamps: false,
  comment: "",
  indexes: []
};
const UserRoles = sequelize.define("sys_user_roles_model", attributes, options);
//   return SysUserRolesModel;
// };
module.exports = UserRoles