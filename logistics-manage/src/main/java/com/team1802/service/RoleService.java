package com.team1802.service;

import com.team1802.dto.*;
import com.team1802.pojo.Node;
import com.team1802.pojo.Role;

import java.util.List;
import java.util.Map;

public interface RoleService {
    boolean addRole(RoleAddDTO dto);

    boolean deleteRole(String name);

    List<Role> listRole();

    Map<String,String> listNameRole();

    boolean updateRole(RoleUpdateDTO dto);

    boolean addMenu(MenuUpdateDTO dto);

    boolean reduceMenu(MenuUpdateDTO dto);

    boolean addUserRole(UserRoleAddDTO dto);

    List<Node> listNode();

    List<Node> getMenuByRoleId(int role_id);

    boolean updateRoleMenu(RoleMenuUpdateDTO dto);
}
