package com.team1802.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;

public class RoleAddDTO {
    private int id;

    private String name;

    private String note;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",  timezone="GMT+8")
    private Timestamp createTime;

    public int getId() {
        return id;
    }

    public RoleAddDTO setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public RoleAddDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getNote() {
        return note;
    }

    public RoleAddDTO setNote(String note) {
        this.note = note;
        return this;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public RoleAddDTO setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
        return this;
    }
}
