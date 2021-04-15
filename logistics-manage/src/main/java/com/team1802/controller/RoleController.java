package com.team1802.controller;

import com.alibaba.fastjson.JSON;
import com.team1802.dto.*;
import com.team1802.pojo.Node;
import com.team1802.pojo.Role;
import com.team1802.service.RoleService;
import com.team1802.utils.TreeUtile;
import com.team1802.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Resource
    RoleService service;

    @ApiOperation(value = "添加角色")
    @PostMapping("/add")
    public ResultVO addRole(@RequestBody RoleAddDTO dto){
        boolean result = service.addRole(dto);
        return result ? ResultVO.SUCCESS.setMessage("添加成功。")
                :ResultVO.FAILED.setMessage("添加失败。");
    }

    @ApiOperation(value = "删除角色")
    @PostMapping("/delete")
    public ResultVO deleteRole(@RequestBody RoleDeleteDTO dto){
        boolean result = service.deleteRole(dto.getName());
        return result ? ResultVO.SUCCESS.setMessage("删除成功。")
                : ResultVO.FAILED.setMessage("删除失败。");
    }

    @ApiOperation(value = "查看角色列表")
    @GetMapping("/listRole")
    public List<Role> listRole(){
        return service.listRole();
    }

    @ApiOperation(value = "查看用户名—角色列表")
    @GetMapping("/listNameRole")
    public Map<String,String> listNameRole(){
        return service.listNameRole();
    }

    @ApiOperation(value = "更新用户角色")
    @PostMapping("/updateRole")
    public ResultVO updateRole(@RequestBody RoleUpdateDTO dto){
        boolean result = service.updateRole(dto);
        return result ? ResultVO.SUCCESS.setMessage("修改成功。")
                : ResultVO.FAILED.setMessage("修改失败。");
    }

    @ApiOperation(value = "增加侧边栏")
    @PostMapping("/addMenu")
    public ResultVO addMenu(@RequestBody MenuUpdateDTO dto){
        boolean result = service.addMenu(dto);
        return result ? ResultVO.SUCCESS.setMessage("添加成功。")
                : ResultVO.FAILED.setMessage("添加失败。");
    }

    @ApiOperation(value = "减少侧边栏")
    @PostMapping("/reduceMenu")
    public ResultVO reduceMenu(@RequestBody MenuUpdateDTO dto){
        boolean result = service.reduceMenu(dto);
        return result ? ResultVO.SUCCESS.setMessage("减少成功。")
                : ResultVO.FAILED.setMessage("减少失败。");
    }

    @ApiOperation(value = "添加用户-角色")
    @PostMapping("/addUserRole")
    public ResultVO addUserRole(@RequestBody UserRoleAddDTO dto){
        boolean result = service.addUserRole(dto);
        return result ? ResultVO.SUCCESS.setMessage("添加成功。")
                : ResultVO.FAILED.setMessage("添加失败。");
    }

    @ApiOperation("获取全部菜单")
    @GetMapping("/menus")
    public String menus(){
        List<Node> nodes = service.listNode();
        return JSON.toJSONString(new TreeUtile().data(nodes));
    }

    @ApiOperation("通过role_id获取菜单")
    @PostMapping("/menu")
    public String menu(@RequestBody RoleGetMenuDTO dto){
        List<Node> nodes = service.getMenuByRoleId(dto.getRole_id());
        return JSON.toJSONString(new TreeUtile().data(nodes));
    }

    @ApiOperation("更新角色权限")
    @PostMapping("/updateRoleMenu")
    public ResultVO updateRoleMenu(@RequestBody RoleMenuUpdateDTO dto){
        boolean result = service.updateRoleMenu(dto);
        return result ? ResultVO.SUCCESS.setMessage("修改权限成功")
                : ResultVO.FAILED.setMessage("修改权限失败");
    }

}
