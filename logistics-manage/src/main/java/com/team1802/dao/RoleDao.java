package com.team1802.dao;

import com.team1802.dto.MenuUpdateDTO;
import com.team1802.dto.RoleUpdateDTO;
import com.team1802.dto.UserRoleAddDTO;
import com.team1802.pojo.Node;
import com.team1802.pojo.Role;
import com.team1802.pojo.UserRoles;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RoleDao {
    @Insert("Insert sys_role(id,name,note,createTime) values (#{role.id},#{role.name},#{role.note},#{role.createTime})")
    int addRole(@Param("role") Role role);

    @Update("Delete From sys_role Where name=#{name}")
    int deleteRole(@Param("name") String name);

    @Select("Select * From sys_role")
    List<Role> listRole();

    @Select("Select user_id,roles_id From sys_user_roles")
    List<UserRoles> listNameRole();

    @Select("Select username From sys_user Where id=#{id}")
    String getUsername(@Param("id") Integer id);

    @Select("Select name From sys_role Where id=#{id}")
    String getRole(@Param("id") Integer id);

    @Update("Update sys_user_roles Set roles_id = (Select id From sys_role Where name = #{dto.new_role}) Where user_id=#{dto.user_id}")
    int updateRole(@Param("dto") RoleUpdateDTO dto);

    @Insert("Insert sys_role_menus(role_id,menu_id) values (#{dto.role_id},#{dto.menu_id})")
    int addMenu(@Param("dto") MenuUpdateDTO dto);

    @Delete("Delete From sys_role_menus Where role_id=#{dto.role_id} and menu_id=#{dto.menu_id}")
    int reduceMenu(@Param("dto") MenuUpdateDTO dto);

    @Insert("Insert sys_user_roles(user_id,roles_id) values (#{dto.user_id},#{dto.role_id})")
    int addUserRole(@Param("dto") UserRoleAddDTO dto);

    @Select("SELECT id,name,parentId,url,icon FROM sys_menus WHERE id IN (SELECT menu_id FROM sys_role_menus WHERE role_id in (SELECT roles_id FROM sys_user_roles))")
    List<Node> listAllMenu();

    @Select("Select id,name,parentId,url,icon FROM sys_menus WHERE id IN (SELECT menu_id FROM sys_role_menus WHERE role_id = #{role_id})")
    List<Node> getMenuByRoleId(@Param("role_id") int role_id);

    @Delete("Delete From sys_role_menus Where role_id=#{role_id}")
    int deleteRoleMenu(int role_id);

    @Insert("Insert sys_role_menus(role_id,menu_id) values (#{role_id},#{menu_id})")
    int addRoleMenu(@Param("role_id") int role_id,@Param("menu_id") int menu_id);
}
