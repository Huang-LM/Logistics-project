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
  name: {
    type: DataTypes.STRING(50),
    allowNull: false,
    defaultValue: null,
    primaryKey: false,
    autoIncrement: false,
    comment: "菜单名称",
    field: "name"
  },
  url: {
    type: DataTypes.STRING(200),
    allowNull: true,
    defaultValue: null,
    primaryKey: false,
    autoIncrement: false,
    comment: "路径",
    field: "url"
  },
  icon: {
    type: DataTypes.STRING(255),
    allowNull: true,
    defaultValue: null,
    primaryKey: false,
    autoIncrement: false,
    comment: "图标",
    field: "icon"
  },
  note: {
    type: DataTypes.STRING(100),
    allowNull: true,
    defaultValue: null,
    primaryKey: false,
    autoIncrement: false,
    comment: "备注",
    field: "note"
  },
  parentId: {
    type: DataTypes.INTEGER,
    allowNull: false,
    defaultValue: null,
    primaryKey: false,
    autoIncrement: false,
    comment: "父id",
    field: "parentId"
  },
  createTime: {
    type: DataTypes.DATE,
    allowNull: false,
    defaultValue: Sequelize.literal('CURRENT_TIMESTAMP'),
    primaryKey: false,
    autoIncrement: false,
    comment: "创建时间",
    field: "createTime"
  },
  updateTime: {
    type: DataTypes.DATE,
    allowNull: false,
    defaultValue: Sequelize.literal('CURRENT_TIMESTAMP'),
    primaryKey: false,
    autoIncrement: false,
    comment: "修改时间",
    field: "updateTime"
  }
};
const options = {
  tableName: "sys_menus",
  comment: "",
  createdAt: "createTime",
  updatedAt: "updateTime",
  indexes: []
};
const Menus = sequelize.define("sys_menus_model", attributes, options);

module.exports = Menus