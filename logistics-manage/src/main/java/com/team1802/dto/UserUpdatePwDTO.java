package com.team1802.dto;

public class UserUpdatePwDTO {

    private int id;

    private String password;

    private String new_password;

    public String getNew_password() {
        return new_password;
    }

    public UserUpdatePwDTO setNew_password(String new_password) {
        this.new_password = new_password;
        return this;
    }

    public int getId() {
        return id;
    }

    public UserUpdatePwDTO setId(int id) {
        this.id = id;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserUpdatePwDTO setPassword(String password) {
        this.password = password;
        return this;
    }

}
