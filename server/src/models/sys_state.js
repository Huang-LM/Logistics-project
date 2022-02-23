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
    state: {
      type: DataTypes.STRING(11),
      allowNull: false,
      defaultValue: null,
      primaryKey: false,
      autoIncrement: false,
      comment: "事件状态",
      field: "state"
    },
    state_info: {
      type: DataTypes.STRING(255),
      allowNull: false,
      defaultValue: null,
      primaryKey: false,
      autoIncrement: false,
      comment: "事件状态描述",
      field: "state_info"
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
    tableName: "sys_state",
    comment: "",
    indexes: []
  };
  const SysStateModel = sequelize.define("sys_state_model", attributes, options);
  return SysStateModel;
};