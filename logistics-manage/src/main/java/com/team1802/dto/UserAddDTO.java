package com.team1802.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;

/**
 * @author fengmingxian
 * Created on 2020-12-27
 */
public class UserAddDTO {
    private String username;

    private String password;

    private String phone;

    private int gender;

    private String email;

    private boolean verify;

    private String role;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",  timezone="GMT+8")
    private Timestamp createTime;

    private int code;


    public int getCode() {
        return code;
    }

    public UserAddDTO setCode(int code) {
        this.code = code;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserAddDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserAddDTO setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public UserAddDTO setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public Integer getGender() {
        return gender;
    }

    public UserAddDTO setGender(Integer gender) {
        this.gender = gender;
        return this;
    }

    public boolean isVerify() {
        return verify;
    }

    public UserAddDTO setVerify(boolean verify) {
        this.verify = verify;
        return this;
    }

    public String getRole() {
        return role;
    }

    public UserAddDTO setRole(String role) {
        this.role = role;
        return this;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public UserAddDTO setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserAddDTO setEmail(String email) {
        this.email = email;
        return this;
    }
}
