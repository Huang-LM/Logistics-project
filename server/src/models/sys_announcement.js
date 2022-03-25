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
  announcement_title: {
    type: DataTypes.STRING(255),
    allowNull: false,
    defaultValue: null,
    primaryKey: false,
    autoIncrement: false,
    comment: "名称",
    field: "announcement_title"
  },
  announcement_body: {
    type: DataTypes.STRING(255),
    allowNull: false,
    defaultValue: null,
    primaryKey: false,
    autoIncrement: false,
    comment: "公告文件",
    field: "announcement_body"
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
  tableName: "sys_announcement",
  createdAt: "createTime",
  updatedAt: "updateTime",
  comment: "",
  indexes: []
};
//   const SysDictionariesModel = sequelize.define("sys_dictionaries_model", attributes, options);
//   return SysDictionariesModel;
// };
const Announce = sequelize.define("sys_announcement_model", attributes, options);

module.exports = Announce