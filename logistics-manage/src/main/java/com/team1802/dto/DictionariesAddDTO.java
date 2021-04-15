package com.team1802.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;

public class DictionariesAddDTO {
    private String name;

    private int parentId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",  timezone="GMT+8")
    private Timestamp createTime;

    public String getName() {
        return name;
    }

    public DictionariesAddDTO setName(String name) {
        this.name = name;
        return this;
    }

    public int getParentId() {
        return parentId;
    }

    public DictionariesAddDTO setParentId(int parentId) {
        this.parentId = parentId;
        return this;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public DictionariesAddDTO setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
        return this;
    }
}
