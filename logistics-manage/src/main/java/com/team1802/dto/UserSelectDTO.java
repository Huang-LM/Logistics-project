package com.team1802.dto;

public class UserSelectDTO {
    private Integer id;
    private String username;
    private String phone;
    private Integer gender;
    private String role;

    public Integer getId() {
        return id;
    }

    public UserSelectDTO setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getGender() {
        return gender;
    }

    public UserSelectDTO setGender(Integer gender) {
        this.gender = gender;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserSelectDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public UserSelectDTO setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getRole() {
        return role;
    }

    public UserSelectDTO setRole(String role) {
        this.role = role;
        return this;
    }

    @Override
    public String toString() {
        return "UserSelectDTO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", phone='" + phone + '\'' +
                ", gender=" + gender +
                ", role='" + role + '\'' +
                '}';
    }
}
