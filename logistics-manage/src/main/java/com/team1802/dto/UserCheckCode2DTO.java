package com.team1802.dto;

public class UserCheckCode2DTO {
    private String email;

    private int code;

    public String getEmail() {
        return email;
    }

    public UserCheckCode2DTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public int getCode() {
        return code;
    }

    public UserCheckCode2DTO setCode(int code) {
        this.code = code;
        return this;
    }
}
