const {
  DataTypes, Sequelize
} = require('sequelize');
var moment = require('moment');
const sequelize = require('../db/seq')

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
  verify: {
    type: DataTypes.INTEGER,
    allowNull: false,
    defaultValue: "0",
    primaryKey: false,
    autoIncrement: false,
    comment: "审核值",
    field: "verify"
  },
  logistics_number: {
    type: DataTypes.BIGINT,
    allowNull: true,
    defaultValue: null,
    primaryKey: false,
    autoIncrement: false,
    comment: "物流编号",
    field: "logistics_number"
  },
  logistics_way: {
    type: DataTypes.STRING(255),
    allowNull: false,
    defaultValue: null,
    primaryKey: false,
    autoIncrement: false,
    comment: "物流运输方式 ",
    field: "logistics_way"
  },
  logistics_way_number: {
    type: DataTypes.STRING(255),
    allowNull: true,
    defaultValue: null,
    primaryKey: false,
    autoIncrement: false,
    comment: "物流运输工具编号",
    field: "logistics_way_number"
  },
  mailing_type: {
    type: DataTypes.STRING(255),
    allowNull: false,
    defaultValue: null,
    primaryKey: false,
    autoIncrement: false,
    comment: "货物类型",
    field: "mailing_type"
  },
  mailing_name: {
    type: DataTypes.STRING(255),
    allowNull: false,
    defaultValue: null,
    primaryKey: false,
    autoIncrement: false,
    comment: "寄件人姓名",
    field: "mailing_name"
  },
  mailing_phone: {
    type: DataTypes.STRING(255),
    allowNull: false,
    defaultValue: null,
    primaryKey: false,
    autoIncrement: false,
    comment: "寄件人电话",
    field: "mailing_phone"
  },
  mailing_address: {
    type: DataTypes.STRING(255),
    allowNull: false,
    defaultValue: null,
    primaryKey: false,
    autoIncrement: false,
    comment: "寄件地址",
    field: "mailing_address"
  },
  shipping_name: {
    type: DataTypes.STRING(255),
    allowNull: false,
    defaultValue: null,
    primaryKey: false,
    autoIncrement: false,
    comment: "收件人姓名",
    field: "shipping_name"
  },
  shipping_phone: {
    type: DataTypes.STRING(255),
    allowNull: false,
    defaultValue: null,
    primaryKey: false,
    autoIncrement: false,
    comment: "收货人电话",
    field: "shipping_phone"
  },
  shipping_address: {
    type: DataTypes.STRING(255),
    allowNull: false,
    defaultValue: null,
    primaryKey: false,
    autoIncrement: false,
    comment: "收货地址",
    field: "shipping_address"
  },
  note: {
    type: DataTypes.STRING(255),
    allowNull: true,
    defaultValue: null,
    primaryKey: false,
    autoIncrement: false,
    comment: "备注",
    field: "note"
  },
  shipping_time: {
    type: DataTypes.DATE,
    allowNull: true,
    defaultValue: null,
    primaryKey: false,
    get() {
      return moment(this.getDataValue('shipping_time')).format('YYYY-MM-DD HH:mm:ss');
    },
    autoIncrement: false,
    comment: "预计到达时间",
    field: "shipping_time"
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
  tableName: "sys_logistics",
  timestamps: false,
  // createdAt: "createTime",
  // updatedAt: "updateTime",
  comment: "",
  indexes: []
};
const Logistics = sequelize.define("sys_logistics_model", attributes, options);

module.exports = Logistics