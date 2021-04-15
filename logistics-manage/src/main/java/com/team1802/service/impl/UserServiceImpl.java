package com.team1802.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import com.team1802.dto.*;
import com.team1802.pojo.Dictionaries;
import com.team1802.pojo.Node;
import com.team1802.utils.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import com.google.common.base.Strings;
import com.team1802.dao.UserDao;
import com.team1802.pojo.User;
import com.team1802.service.UserService;
import com.team1802.vo.ListVO;

import javax.annotation.Resource;

/**
 * @author fengmingxian
 * Created on 2020-12-27
 */

@Lazy
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;
    @Autowired
    RedisUtil redisUtil;

    @Override
    public Integer checkUser(UserLoginDTO dto) {
        if (StringUtils.isBlank(dto.getUsername())) {
            return null;
        }
        User user = userDao.getUserByUsername(dto.getUsername());
        if (Objects.nonNull(user) && StringUtils.equals(dto.getPassword(), user.getPassword())) {
            return user.getId();
        }
        return null;
    }

    @Override
    public User getUser(UserGetDTO dto) {
        return userDao.getUserById(dto.getId());
    }

    @Autowired
    JavaMailSenderImpl mailSender;
    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public boolean sendCode(String emailNo) {
        boolean result = true;
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setSubject("您正在注册账号，您的验证码为...");
        Integer code = (int) (Math.random() * 8998) + 1000 + 1;
        simpleMailMessage.setText("您的验证码为：" + code + "，切勿泄漏给他人。");
        simpleMailMessage.setTo(emailNo);
        simpleMailMessage.setFrom("1213125222@qq.com");

        try {
            mailSender.send(simpleMailMessage);
        } catch (Exception e) {
            result = false;
        }
        if (result == true) {
            redisTemplate.opsForValue().set(emailNo, code, 1, TimeUnit.MINUTES);
        }
        return result;
    }

    @Override
    public boolean sendCode2(String email) {
        boolean result = true;
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setSubject("您正在找回密码，您的验证码为...");
        Integer code=(int)(Math.random()*8998)+1000+1;
        simpleMailMessage.setText("您的验证码为：" + code + "，切勿泄漏给他人。");
        simpleMailMessage.setTo(email);
        simpleMailMessage.setFrom("1213125222@qq.com");

        try {
            mailSender.send(simpleMailMessage);
        }catch (Exception e){
            result = false;
        }
        if(result == true){
            redisTemplate.opsForValue().set(email,code,1,TimeUnit.MINUTES);
        }
        return result;
    }

    @Override
    public boolean registerUser(UserAddDTO dto) {
        if (StringUtils.isBlank(dto.getUsername())
                || StringUtils.isBlank(dto.getPassword())
                || Objects.nonNull(userDao.getUserByUsername(dto.getUsername()))) {
            return false;
        }
        User user = new User().setUsername(dto.getUsername())
                .setPassword(dto.getPassword())
                .setPhone(Strings.nullToEmpty(dto.getPhone()))
                .setGender(dto.getGender())
                .setEmail(dto.getEmail())
                .setVerify(dto.isVerify())
                .setCreateTime(dto.getCreateTime());
        int result1 = userDao.addUser(user);
        int result2 = userDao.addUserRole(userDao.getUserByUsername(dto.getUsername()).getId(), dto.getRole());
        return result1 * result2 > 0;
    }

    @Override
    public boolean deleteUser(int id) {
        return userDao.deleteUser(id) > 0;
    }

    /*
        修改更新
     */
    @Override
    public boolean updateUser(UserUpdateDTO dto) {
        User user = new User().setUsername(dto.getUsername())
                .setPhone(Strings.nullToEmpty(dto.getPhone()))
                .setGender(dto.getGender());
        user.setId(dto.getId());
        return userDao.updateUser(user) > 0;
    }

    @Override
    public ListVO<User> listUser(UserListDTO dto) {
        int page = dto.getPage();
        int pageSize = dto.getPageSize();
        List<User> list = userDao.listUser(pageSize, page * pageSize);
        for (User user : list) {
            user.setRole(userDao.getRoleById(user.getId()));
        }
        ListVO<User> userListVO = new ListVO<User>()
                .setPage(page)
                .setPageSize(pageSize)
                .setList(list)
                .setTotal(userDao.getCount());
        return userListVO;
    }

    @Override
    public List<Node> listNode(UserMenuDTO dto) {
        List<Node> nodes = userDao.getMenu(dto.getId());
        return nodes;
    }

    @Override
    public boolean updateUserPw(UserUpdatePwDTO dto) {
        if (dto.getPassword().equals((userDao.getUserById(dto.getId())).getPassword())) {
            return userDao.updateUserPw(dto) > 0;
        }
        return false;
    }

    @Override
    public boolean updateUserVerify(UserUpdateVerifyDTO dto) {
        return userDao.updateUserVerify(dto) > 0;
    }

    @Override
    public List<User> selectUser(UserSelectDTO dto) {
//        List<User> users = userDao.selectUser(dto);
        String key = "User_Select"+dto;
        boolean hasKey = redisUtil.hasKey(key);
        if (hasKey) {
            List<User> users1 = (List<User>) redisUtil.get(key);
            System.out.println("-------------从Redis中查询----------------");
            return users1;
        } else {
            List<User> users1 = userDao.selectUser(dto);
            for (User user : users1) {
                user.setRole(userDao.getRoleById(user.getId()));
            }
            // 如果查找的用户不为空，则写入缓存
            if (users1 != null) {
                System.out.println("------------写入缓存---------------------");
                //写入缓存
                redisUtil.set(key, users1, 10);
            }
            return users1;
        }
    }

    @Override
    public boolean findPassword(UserFindPasswordDTO dto) {
        return userDao.findPassword(dto) > 0;
    }

    @Override
    public List<String> getAllUsername() {
        return userDao.getAllUsername();
    }

    @Override
    public int getVerify(UserLoginDTO dto) {
        return userDao.getVerify(dto);
    }


}
