package com.team1802.dto;

public class UserRoleAddDTO {
    private int user_id;

    private int role_id;

    public int getUser_id() {
        return user_id;
    }

    public UserRoleAddDTO setUser_id(int user_id) {
        this.user_id = user_id;
        return this;
    }

    public int getRole_id() {
        return role_id;
    }

    public UserRoleAddDTO setRole_id(int role_id) {
        this.role_id = role_id;
        return this;
    }
}
