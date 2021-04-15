package com.team1802.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;

public class MenuUpdateDTO {
    private int role_id;

    private int menu_id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",  timezone="GMT+8")
    private Timestamp createTime;


    public int getRole_id() {
        return role_id;
    }

    public MenuUpdateDTO setRole_id(int role_id) {
        this.role_id = role_id;
        return this;
    }

    public int getMenu_id() {
        return menu_id;
    }

    public MenuUpdateDTO setMenu_id(int menu_id) {
        this.menu_id = menu_id;
        return this;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public MenuUpdateDTO setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
        return this;
    }
}
