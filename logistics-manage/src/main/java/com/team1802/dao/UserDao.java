package com.team1802.dao;

import java.util.List;

import com.team1802.dto.*;
import com.team1802.pojo.Node;
import org.apache.ibatis.annotations.*;

import com.team1802.pojo.User;
import org.springframework.stereotype.Service;


@Mapper
public interface UserDao {

    @Select("SELECT * FROM sys_user WHERE username=#{username}")
    User getUserByUsername(@Param("username") String username);

    @Select("SELECT * FROM sys_user WHERE id=#{id}")
    User getUserById(@Param("id") Integer id);

    @Insert("Insert sys_user(username,password,phone,gender,email,verify,createTime) values(#{user.username},#{user.password},#{user.phone},#{user.gender},#{user.email},"
            + "#{user.verify},#{user.createTime})")
    int addUser(@Param("user") User user);

    @Insert("Insert sys_user_roles(user_id,roles_id) values (#{id},(Select id From sys_role Where name=#{role}))")
    int addUserRole(@Param("id") int id,@Param("role") String role);

    @Delete("DELETE FROM sys_user WHERE id = #{id}")
    int deleteUser(@Param("id") int id);

    @Update("Update sys_user set username = #{user.username},phone = #{user.phone},gender = #{user.gender} WHERE id = #{user.id}")
    int updateUser(@Param("user") User user);

    @Select("SELECT * FROM sys_user LIMIT #{limit} OFFSET #{offset}")
    List<User> listUser(@Param("limit") int limit, @Param("offset") int offset);

    @Select("Select name From sys_role Where id = (Select roles_id From sys_user_roles Where user_id = #{user_id})")
    String getRoleById(@Param("user_id") int user_id);

    @Select("SELECT count(*) FROM sys_user")
    int getCount();

    @Select("SELECT id,name,parentId,url,icon FROM sys_menus WHERE id IN (SELECT menu_id FROM sys_role_menus WHERE role_id=(SELECT roles_id FROM sys_user_roles WHERE user_id=#{id})) ")
    List<Node> getMenu(@Param("id") int id);

    @Update("Update sys_user set password = #{dto.new_password } Where id = #{dto.id}")
    int updateUserPw(@Param("dto") UserUpdatePwDTO dto);

    @Update("UPDATE sys_user SET verify = (IF(#{dto.verify}=0,1,0)) WHERE id = #{dto.id}")
    int updateUserVerify(@Param("dto") UserUpdateVerifyDTO dto);

    @Select("Select * From sys_user Where (id=#{dto.id} or #{dto.id} is null) And (username=#{dto.username} or #{dto.username} = '') And (phone=#{dto.phone} or #{dto.phone} = '') And (gender=#{dto.gender} or #{dto.gender} is null) And (id in (Select user_id From sys_user_roles Where roles_id = (Select id From sys_role Where name=#{dto.role}))or #{dto.role} = '')")
    List<User> selectUser(@Param("dto") UserSelectDTO dto);

    @Update("Update sys_user Set password = #{dto.password} Where email = #{dto.email}")
    int findPassword(@Param("dto") UserFindPasswordDTO dto);

    @Select("Select username From sys_user")
    List<String> getAllUsername();

    @Select("Select verify From sys_user Where username = #{dto.username} and password = #{dto.password}")
    int getVerify(@Param("dto")UserLoginDTO dto);
}
