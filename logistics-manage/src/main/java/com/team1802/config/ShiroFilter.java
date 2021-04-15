package com.team1802.config;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class ShiroFilter extends FormAuthenticationFilter {

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        //这里只有返回false才会执行onAccessDenied方法,因为
        // return super.isAccessAllowed(request, response, mappedValue);
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) {

        String token = getRequestToken((HttpServletRequest) request);
        String login = ((HttpServletRequest) request).getServletPath();

        //如果为登录,就放行
        if ("/user/login".equals(login)){
            return true;
        }
        if (StringUtils.isBlank(token)){
            System.out.println("没有token");
            return false;
        }

        //从当前shiro中获得用户信息
        System.out.println(SecurityUtils.getSubject().getPrincipal()+"---------------------------------------");
        UsernamePasswordToken token1 = (UsernamePasswordToken)SecurityUtils.getSubject().getPrincipal();
        String salt = "wjw";//盐
        Integer hashIterations = 2;//散列次数
        if (new Md5Hash(token1.toString(),salt,hashIterations).toString().equals(token)){
            return true;
        }else{
            System.out.println("无效token");
        }
        return false;
    }
    private String getRequestToken(HttpServletRequest request) {
        //默认从请求头中获得token
        String token = request.getHeader("token");
        //如果header中不存在token，则从参数中获取token
        if(StringUtils.isBlank(token)){
            token = request.getParameter("token");
        }
        return token;
    }
}
