package com.team1802.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;

public class State {
    private int id;

    private String state;

    private String state_info;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",  timezone="GMT+8")
    private Timestamp createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",  timezone="GMT+8")
    private Timestamp updateTime;

    public int getId() {
        return id;
    }

    public State setId(int id) {
        this.id = id;
        return this;
    }

    public String getState() {
        return state;
    }

    public State setState(String state) {
        this.state = state;
        return this;
    }

    public String getState_info() {
        return state_info;
    }

    public State setState_info(String state_info) {
        this.state_info = state_info;
        return this;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public State setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
        return this;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public State setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
        return this;
    }
}
