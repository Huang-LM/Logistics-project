package com.team1802.dto;

public class UserLogisticsAddDTO {
    private int user_id;

    private int logistics_id;

    public int getUser_id() {
        return user_id;
    }

    public UserLogisticsAddDTO setUser_id(int user_id) {
        this.user_id = user_id;
        return this;
    }

    public int getLogistics_id() {
        return logistics_id;
    }

    public UserLogisticsAddDTO setLogistics_id(int logistics_id) {
        this.logistics_id = logistics_id;
        return this;
    }
}
