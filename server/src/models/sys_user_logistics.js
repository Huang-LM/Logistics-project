const {
  DataTypes
} = require('sequelize');

module.exports = sequelize => {
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
    logistics_id: {
      type: DataTypes.INTEGER,
      allowNull: false,
      defaultValue: null,
      primaryKey: false,
      autoIncrement: false,
      comment: "物流id",
      field: "logistics_id"
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
    createTime: {
      type: DataTypes.DATE,
      allowNull: false,
      defaultValue: sequelize.literal('CURRENT_TIMESTAMP'),
      primaryKey: false,
      autoIncrement: false,
      comment: null,
      field: "createTime"
    },
    updateTime: {
      type: DataTypes.DATE,
      allowNull: false,
      defaultValue: sequelize.literal('CURRENT_TIMESTAMP'),
      primaryKey: false,
      autoIncrement: false,
      comment: null,
      field: "updateTime"
    }
  };
  const options = {
    tableName: "sys_user_logistics",
    comment: "",
    indexes: []
  };
  const SysUserLogisticsModel = sequelize.define("sys_user_logistics_model", attributes, options);
  return SysUserLogisticsModel;
};