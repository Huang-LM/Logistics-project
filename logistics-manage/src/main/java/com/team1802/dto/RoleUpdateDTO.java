package com.team1802.dto;

public class RoleUpdateDTO {
    private int user_id;

    private String new_role;

    public int getUser_id() {
        return user_id;
    }

    public RoleUpdateDTO setUser_id(int user_id) {
        this.user_id = user_id;
        return this;
    }

    public String getNew_role() {
        return new_role;
    }

    public RoleUpdateDTO setNew_role(String new_role) {
        this.new_role = new_role;
        return this;
    }
}
