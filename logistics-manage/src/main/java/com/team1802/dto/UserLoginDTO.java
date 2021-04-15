package com.team1802.dto;

import org.springframework.lang.NonNull;

/**
 * @author fengmingxian
 * Created on 2020-12-27
 */

public class UserLoginDTO {

    private String username;

    private String password;


    public String getUsername() {
        return username;
    }

    public UserLoginDTO setUsername(@NonNull String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserLoginDTO setPassword(String password) {
        this.password = password;
        return this;
    }


}
