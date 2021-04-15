package com.team1802.config;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfiguration {
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(defaultWebSecurityManager);

        Map<String, Filter> filterMap = new LinkedHashMap<>();
//        filterMap.put("/role/*","authc");
//        filterMap.put("/user/*","authc");
//        filterMap.put("/logisticsInfo/*","authc");
//        filterMap.put("/dictionaries/*","authc");
        filterMap.put("auth",new ShiroFilter());
        bean.setFilters(filterMap);
        Map<String,String> map = new LinkedHashMap<>();
        map.put("/user/id","anon");
        map.put("/user/add","anon");
        map.put("/user/code","anon");
        map.put("/user/code2","anon");
        map.put("/user/checkCode","anon");
        map.put("/user/findPassword","anon");
        map.put("/user/*","auth");
        map.put("/role/*","auth");
        map.put("/logisticsInfo/*","auth");
        map.put("/dictionaries/*","auth");
        bean.setLoginUrl("/user/login");
        bean.setFilterChainDefinitionMap(map);
        return bean;
    }

    @Bean(name="securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(userRealm);
        return defaultWebSecurityManager;
    }

    @Bean
    public UserRealm userRealm(){
        return new UserRealm();
    }


}
