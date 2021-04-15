package com.team1802.service.impl;

import com.team1802.dao.RoleDao;
import com.team1802.dto.*;
import com.team1802.pojo.Node;
import com.team1802.pojo.Role;
import com.team1802.pojo.UserRoles;
import com.team1802.service.RoleService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Lazy
@Service
public class RoleServiceImpl implements RoleService {
    @Resource
    RoleDao dao;
    @Override
    public boolean addRole(RoleAddDTO dto) {
        Role role=new Role().setId(dto.getId())
                .setName(dto.getName())
                .setNote(dto.getNote())
                .setCreateTime(dto.getCreateTime());
        return dao.addRole(role)>0;
    }

    @Override
    public boolean deleteRole(String name) {
        return dao.deleteRole(name)>0;
    }

    @Override
    public List<Role> listRole() {
        return dao.listRole();
    }

    @Override
    public Map<String,String> listNameRole() {
        List<UserRoles> list=dao.listNameRole();
        List<String> names=new ArrayList<>();
        List<String> roles=new ArrayList<>();
        Map<String,String> map=new LinkedHashMap<>();
        for (UserRoles userRoles:list) {
            names.add(dao.getUsername(userRoles.getUser_id()));
            roles.add(dao.getRole(userRoles.getRoles_id()));
        }
        for(int i=0;i<list.size();i++){
            map.put(names.get(i),roles.get(i));
        }
        return map;
    }

    @Override
    public boolean updateRole(RoleUpdateDTO dto) {
        return dao.updateRole(dto) > 0;
    }

    @Override
    public boolean addMenu(MenuUpdateDTO dto) {
        return dao.addMenu(dto) > 0;
    }

    @Override
    public boolean reduceMenu(MenuUpdateDTO dto) {
        return dao.reduceMenu(dto) > 0;
    }

    @Override
    public boolean addUserRole(UserRoleAddDTO dto) {
        return dao.addUserRole(dto) > 0;
    }

    @Override
    public List<Node> listNode() {
        List<Node> nodes = dao.listAllMenu();
        return nodes;
    }

    @Override
    public List<Node> getMenuByRoleId(int role_id) {
        List<Node> nodes = dao.getMenuByRoleId(role_id);
        return nodes;
    }

    @Override
    public boolean updateRoleMenu(RoleMenuUpdateDTO dto) {
        int result1 = dao.deleteRoleMenu(dto.getRole_id());
        int result2 = 1;
        for (int menu_id : dto.getMenu_id()) {
            result2 *= dao.addRoleMenu(dto.getRole_id(),menu_id);
        }
        return result1 * result2> 0;
    }


}
