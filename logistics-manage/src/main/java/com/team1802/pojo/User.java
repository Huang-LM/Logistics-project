package com.team1802.pojo;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;

@ApiModel(value = "User对象", description = "用户对象")
public class User {
    private int id;

    private String username;

    private String password;

    private String phone;

    private int gender;

    private String email;

    private boolean verify;

    private String role;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",  timezone="GMT+8")
    private Timestamp createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",  timezone="GMT+8")
    private Timestamp updateTime;

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public User setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public Integer getGender() {
        return gender;
    }

    public User setGender(Integer gender) {
        this.gender = gender;
        return this;
    }

    public String getRole() {
        return role;
    }

    public User setRole(String role) {
        this.role = role;
        return this;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public User setCreateTime(Timestamp createTime){
        this.createTime=createTime;
        return this;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public User setUpdateTime(Timestamp updateTime){
        this.updateTime=updateTime;
        return this;
    }

    public boolean isVerify() {
        return verify;
    }

    public User setVerify(boolean verify) {
        this.verify = verify;
        return this;
    }
}
