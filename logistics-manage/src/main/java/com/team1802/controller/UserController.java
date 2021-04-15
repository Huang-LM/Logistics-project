package com.team1802.controller;

import com.alibaba.fastjson.JSON;
import com.team1802.dto.*;
import com.team1802.pojo.Node;
import com.team1802.utils.TreeUtile;
import io.swagger.models.auth.In;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import com.team1802.pojo.User;
import com.team1802.service.UserService;
import com.team1802.vo.ListVO;
import com.team1802.vo.ResultVO;

import io.swagger.annotations.ApiOperation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "用户登录")
    @PostMapping("/login")
    public Map<String,Object> login(@RequestBody UserLoginDTO dto) {
        Map<String,Object> map = new HashMap<>();
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(dto.getUsername(),dto.getPassword());
        try {
            subject.login(token);
            if (userService.getVerify(dto) == 0){
                map.put("code",0);
                map.put("message","您的账号未通过审核");
                return map;
            }else{
                map.put("code",1);
                map.put("message","登陆成功");
                String salt = "wjw";//盐
                Integer hashIterations = 2;//散列次数
                map.put("token",new Md5Hash(token.toString(),salt,hashIterations).toString());
                return map;
            }
        }catch (UnknownAccountException e){
            map.put("code",0);
            map.put("message","用户名不存在，登录失败");
            return map;
        }catch (IncorrectCredentialsException e){
            map.put("code",0);
            map.put("message","密码错误，登陆失败");
            return map;
        }
    }

    @ApiOperation("用户注销")
    @GetMapping("/logout")
    public ResultVO logout(){
        SecurityUtils.getSubject().logout();
        return  ResultVO.SUCCESS.setMessage("注销成功");
    }

    @ApiOperation(value = "获取用户id")
    @PostMapping("/id")
    public Integer getId(@RequestBody UserLoginDTO dto){
        return userService.checkUser(dto);
    }

//        Integer id = userService.checkUser(dto);
//        Map<String, Object> map = new HashMap<>();
//        if (id == null) {
//            return JSON.toJSONString(ResultVO.FAILED.setMessage("用户名或密码错误，登录失败"));
//        }else if ((userService.getUser(new UserGetDTO().setId(id))).isVerify() == false){
//            return JSON.toJSONString(ResultVO.FAILED.setMessage("该用户未通过审核，登录失败"));
//        }
//        else {
//            ResultVO resultVO = ResultVO.SUCCESS.setMessage("登录成功");
//            map.put("code", resultVO.getCode());
//            map.put("message", resultVO.getMessage());
//            map.put("id", id);
//            return JSON.toJSONString(map);
//        }


    @ApiOperation(value = "获取用户信息")
    @PostMapping("/get")
    public User getUser(@RequestBody UserGetDTO userGetDTO){
        User user=userService.getUser(userGetDTO);
        return user;
    }

    @ApiOperation("注册时，获取验证码")
    @PostMapping("/code")
    public ResultVO code(@RequestBody UserGetCodeDTO dto){
        boolean result = userService.sendCode(dto.getEmailNo());
        return result ? ResultVO.SUCCESS.setMessage("验证码发送成功")
                : ResultVO.FAILED.setMessage("验证码发送失败，请核实邮箱账号。");
    }

    @ApiOperation("找回密码时，获取验证码")
    @PostMapping("/code2")
    public ResultVO code2(@RequestBody UserGetCodeDTO dto){
        boolean result = userService.sendCode2(dto.getEmailNo());
        return result ? ResultVO.SUCCESS.setMessage("验证码发送成功")
                : ResultVO.FAILED.setMessage("验证码发送失败，请核实邮箱账号。");
    }

    @Autowired
    RedisTemplate redisTemplate;

    @ApiOperation(value = "用户注册")
    @PostMapping("/add")
    public ResultVO addUser(@RequestBody UserAddDTO userAddDTO) {
        if(!Objects.isNull(redisTemplate.opsForValue().get(userAddDTO.getEmail()))) {
           if(Objects.equals(redisTemplate.opsForValue().get(userAddDTO.getEmail()), userAddDTO.getCode())) {
               boolean result2 = userService.registerUser(userAddDTO);
               return result2 ? ResultVO.SUCCESS.setMessage("用户注册成功")
                       : ResultVO.FAILED.setMessage("用户注册失败");
           }else {
               return ResultVO.FAILED.setMessage("验证码不正确，请重新输入");
           }
        }else {
            return ResultVO.FAILED.setMessage("验证码已过期");
        }
    }

    @ApiOperation("管理员注册用户")
    @PostMapping("/managerAddUser")
    public ResultVO manager(@RequestBody UserAddDTO userAddDTO){
        boolean result = userService.registerUser(userAddDTO);

        return result ? ResultVO.SUCCESS.setMessage("注册成功")
                : ResultVO.FAILED.setMessage("注册失败");
    }

    @ApiOperation(value = "删除用户")
    @PostMapping("/delete")
    public ResultVO deleteUser(@RequestBody UserDeleteDTO dto){
        boolean result=userService.deleteUser(dto.getId());

        return result?ResultVO.SUCCESS.setMessage("用户删除成功")
                     :ResultVO.FAILED.setMessage("用户删除失败");
    }

    /*
        修改，更新操作
     */
    @ApiOperation(value = "更新数据")
    @PostMapping("/update")
    public ResultVO updateUser(@RequestBody UserUpdateDTO dto) {
        boolean result =userService.updateUser(dto);

        return result ? ResultVO.SUCCESS.setMessage("用户修改成功")
                : ResultVO.FAILED.setMessage("用户修改失败");
    }


    @ApiOperation(value = "获取用户列表")
    @PostMapping("/list")
    public ListVO<User> listUser(@RequestBody UserListDTO dto){
        return userService.listUser(dto);
    }


    @ApiOperation(value = "获取用户菜单")
    @PostMapping("/menu")
    public String menu(@RequestBody UserMenuDTO dto){
        List<Node> list=userService.listNode(dto);
        return JSON.toJSONString(new TreeUtile().data(list));
    }


    @ApiOperation(value = "修改用户密码")
    @PostMapping("/updatepw")
    public ResultVO updateUserPw(@RequestBody UserUpdatePwDTO dto){
        boolean result = userService.updateUserPw(dto);
        return result ? ResultVO.SUCCESS.setMessage("修改密码成功")
                : ResultVO.FAILED.setMessage("修改密码失败");
    }

    @ApiOperation("核对验证码")
    @PostMapping("/checkCode")
    public ResultVO checkCode(@RequestBody UserCheckCode2DTO dto){
        if(!Objects.isNull(redisTemplate.opsForValue().get(dto.getEmail()))) {
            if(Objects.equals(redisTemplate.opsForValue().get(dto.getEmail()), dto.getCode())) {
                return ResultVO.SUCCESS.setMessage("验证成功");
            }else {
                return ResultVO.FAILED.setMessage("验证码不正确，请重新输入");
            }
        }else {
            return ResultVO.FAILED.setMessage("验证码已过期");
        }
    }

    @ApiOperation("找回密码")
    @PostMapping("/findPassword")
    public ResultVO findPassword(@RequestBody UserFindPasswordDTO dto){
        boolean result = userService.findPassword(dto);

        return result ? ResultVO.SUCCESS.setMessage("修改密码成功")
                : ResultVO.FAILED.setMessage("修改密码成功");
    }


    @ApiOperation("修改用户权限")
    @PostMapping("/examine")
    public ResultVO examine(@RequestBody UserUpdateVerifyDTO dto){
        boolean result = userService.updateUserVerify(dto);
        return result ? ResultVO.SUCCESS.setMessage("审核修改成功")
                : ResultVO.FAILED.setMessage("审核修改失败");
    }


    @ApiOperation("按条件查找用户")
    @PostMapping("/select")
    public List<User> select(@RequestBody UserSelectDTO dto){
        return userService.selectUser(dto);
    }

}
