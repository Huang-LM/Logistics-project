package com.team1802.config;

import com.team1802.dao.UserDao;
import com.team1802.dto.UserLoginDTO;
import com.team1802.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

public class UserRealm extends AuthorizingRealm {

    @Autowired
    UserService userService;
    @Resource
    UserDao userDao;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;
        List<String> usernames = userService.getAllUsername();
        boolean result = false;
        for (String username : usernames) {
            if (username.equals(token.getUsername())){
                result = true;
            }
        }
        if (!result){
            return null;
        }
        return new SimpleAuthenticationInfo(token,userDao.getUserByUsername(token.getUsername()).getPassword(),"");
    }
}
