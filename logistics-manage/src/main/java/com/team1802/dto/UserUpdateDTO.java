package com.team1802.dto;

public class  UserUpdateDTO {
    private String username;

    private String phone;

    private int gender;

    private int id;

    public Integer getId() {
        return id;
    }

    public UserUpdateDTO setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserUpdateDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public UserUpdateDTO setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public Integer getGender() {
        return gender;
    }

    public UserUpdateDTO setGender(Integer gender) {
        this.gender = gender;
        return this;
    }

}
