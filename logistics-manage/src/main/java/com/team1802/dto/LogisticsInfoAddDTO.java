package com.team1802.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;

public class LogisticsInfoAddDTO {

    private int user_id;

    private String logistics_way;

    private String mailing_type;

    private String mailing_name;

    private String mailing_phone;

    private String mailing_address;

    private String shipping_name;

    private String shipping_phone;

    private String shipping_address;

    private String note;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",  timezone="GMT+8")
    private Timestamp createTime;


    public String getLogistics_way() {
        return logistics_way;
    }

    public LogisticsInfoAddDTO setLogistics_way(String logistics_way) {
        this.logistics_way = logistics_way;
        return this;
    }

    public String getMailing_type() {
        return mailing_type;
    }

    public LogisticsInfoAddDTO setMailing_type(String mailing_type) {
        this.mailing_type = mailing_type;
        return this;
    }

    public String getMailing_phone() {
        return mailing_phone;
    }

    public LogisticsInfoAddDTO setMailing_phone(String mailing_phone) {
        this.mailing_phone = mailing_phone;
        return this;
    }

    public String getMailing_address() {
        return mailing_address;
    }

    public LogisticsInfoAddDTO setMailing_address(String mailing_address) {
        this.mailing_address = mailing_address;
        return this;
    }

    public String getShipping_phone() {
        return shipping_phone;
    }

    public LogisticsInfoAddDTO setShipping_phone(String shipping_phone) {
        this.shipping_phone = shipping_phone;
        return this;
    }

    public String getShipping_address() {
        return shipping_address;
    }

    public LogisticsInfoAddDTO setShipping_address(String shipping_address) {
        this.shipping_address = shipping_address;
        return this;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public LogisticsInfoAddDTO setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
        return this;
    }

    public String getMailing_name() {
        return mailing_name;
    }

    public LogisticsInfoAddDTO setMailing_name(String mailing_name) {
        this.mailing_name = mailing_name;
        return this;
    }

    public String getShipping_name() {
        return shipping_name;
    }

    public LogisticsInfoAddDTO setShipping_name(String shipping_name) {
        this.shipping_name = shipping_name;
        return this;
    }

    public String getNote() {
        return note;
    }

    public LogisticsInfoAddDTO setNote(String note) {
        this.note = note;
        return this;
    }

    public int getUser_id() {
        return user_id;
    }

    public LogisticsInfoAddDTO setUser_id(int user_id) {
        this.user_id = user_id;
        return this;
    }
}
