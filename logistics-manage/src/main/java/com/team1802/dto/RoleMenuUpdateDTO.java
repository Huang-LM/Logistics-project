package com.team1802.dto;

public class RoleMenuUpdateDTO {
    private int role_id;

    private int[] menu_id;

    public int getRole_id() {
        return role_id;
    }

    public RoleMenuUpdateDTO setRole_id(int role_id) {
        this.role_id = role_id;
        return this;
    }

    public int[] getMenu_id() {
        return menu_id;
    }

    public RoleMenuUpdateDTO setMenu_id(int[] menu_id) {
        this.menu_id = menu_id;
        return this;
    }
}
