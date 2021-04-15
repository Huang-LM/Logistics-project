package com.team1802.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;

public class UserRoles {
    private int id;

    private int user_id;

    private int roles_id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",  timezone="GMT+8")
    private Timestamp createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",  timezone="GMT+8")
    private Timestamp updateTime;

    public int getId() {
        return id;
    }

    public UserRoles setId(int id) {
        this.id = id;
        return this;
    }

    public int getUser_id() {
        return user_id;
    }

    public UserRoles setUser_id(int user_id) {
        this.user_id = user_id;
        return this;
    }

    public int getRoles_id() {
        return roles_id;
    }

    public UserRoles setRoles_id(int roles_id) {
        this.roles_id = roles_id;
        return this;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public UserRoles setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
        return this;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public UserRoles setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
        return this;
    }
}
