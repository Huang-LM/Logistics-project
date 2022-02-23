const {
  DataTypes, Sequelize
} = require('sequelize');

const seq = require('../db/seq')

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
    username: {
      type: DataTypes.STRING(255),
      allowNull: false,
      defaultValue: null,
      primaryKey: false,
      autoIncrement: false,
      comment: "用户名",
      field: "username",
      unique: "username_2"
    },
    password: {
      type: DataTypes.STRING(255),
      allowNull: false,
      defaultValue: null,
      primaryKey: false,
      autoIncrement: false,
      comment: "密码",
      field: "password"
    },
    phone: {
      type: DataTypes.STRING(255),
      allowNull: false,
      defaultValue: null,
      primaryKey: false,
      autoIncrement: false,
      comment: "手机号",
      field: "phone"
    },
    gender: {
      type: DataTypes.INTEGER,
      allowNull: true,
      defaultValue: null,
      primaryKey: false,
      autoIncrement: false,
      comment: "性别：女0，男1",
      field: "gender"
    },
    email: {
      type: DataTypes.STRING(255),
      allowNull: false,
      defaultValue: null,
      primaryKey: false,
      autoIncrement: false,
      comment: "邮箱地址",
      field: "email"
    },
    verify: {
      type: DataTypes.INTEGER(1).UNSIGNED.ZEROFILL,
      allowNull: false,
      defaultValue: "0",
      primaryKey: false,
      autoIncrement: false,
      comment: "审核值：未通过0，通过为1",
      field: "verify"
    },
    createTime: {
      type: DataTypes.DATE,
      allowNull: false,
      defaultValue: Sequelize.literal('CURRENT_TIMESTAMP'),
      primaryKey: false,
      autoIncrement: false,
      comment: null,
      field: "createTime"
    },
    updateTime: {
      type: DataTypes.DATE,
      allowNull: false,
      defaultValue: Sequelize.literal('CURRENT_TIMESTAMP'),
      primaryKey: false,
      autoIncrement: false,
      comment: null,
      field: "updateTime"
    }
  };
  const options = {
    tableName: "sys_user",
    timestamps: false,
    comment: "",
    indexes: []
  };
  const User = seq.define("sys_user_model", attributes, options);
//   return SysUserModel;
// };

module.exports = User