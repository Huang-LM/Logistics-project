package com.team1802.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;

public class LogisticsStateAddDTO {
    private int logistics_id;

    private int state_id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",  timezone="GMT+8")
    private Timestamp create_time;

    public int getLogistics_id() {
        return logistics_id;
    }

    public LogisticsStateAddDTO setLogistics_id(int logistics_id) {
        this.logistics_id = logistics_id;
        return this;
    }

    public int getState_id() {
        return state_id;
    }

    public LogisticsStateAddDTO setState_id(int state_id) {
        this.state_id = state_id;
        return this;
    }

    public Timestamp getCreate_time() {
        return create_time;
    }

    public LogisticsStateAddDTO setCreate_time(Timestamp create_time) {
        this.create_time = create_time;
        return this;
    }
}
