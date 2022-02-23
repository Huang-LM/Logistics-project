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
    tableName: "sys_user_roles",
    comment: "",
    indexes: []
  };
  const SysUserRolesModel = sequelize.define("sys_user_roles_model", attributes, options);
  return SysUserRolesModel;
};