package com.team1802.dto;

public class UserFindPasswordDTO {
    private String email;

    private String password;

    public String getEmail() {
        return email;
    }

    public UserFindPasswordDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserFindPasswordDTO setPassword(String password) {
        this.password = password;
        return this;
    }
}
