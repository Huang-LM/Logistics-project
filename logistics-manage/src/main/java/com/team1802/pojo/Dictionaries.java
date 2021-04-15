package com.team1802.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;

public class Dictionaries {
    private int id;

    private String name;

    private int parentId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",  timezone="GMT+8")
    private Timestamp createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",  timezone="GMT+8")
    private Timestamp updateTime;

    public int getId() {
        return id;
    }

    public Dictionaries setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Dictionaries setName(String name) {
        this.name = name;
        return this;
    }

    public int getParentId() {
        return parentId;
    }

    public Dictionaries setParentId(int parentId) {
        this.parentId = parentId;
        return this;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public Dictionaries setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
        return this;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public Dictionaries setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
        return this;
    }
}
