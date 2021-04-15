package com.team1802.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;

public class LogisticsInfo {
    private int id;

    private int verify;

    private int logistics_number;

    private String logistics_way;

    private String  logistics_way_number;

    private String mailing_type;

    private String mailing_name;

    private String mailing_phone;

    private String mailing_address;

    private String shipping_name;

    private String shipping_phone;

    private String shipping_address;

    private String note;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",  timezone="GMT+8")
    private Timestamp shipping_time;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",  timezone="GMT+8")
    private Timestamp createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",  timezone="GMT+8")
    private Timestamp updateTime;

    public int getId() {
        return id;
    }

    public LogisticsInfo setId(int id) {
        this.id = id;
        return this;
    }

    public int getVerify() {
        return verify;
    }

    public LogisticsInfo setVerify(int verify) {
        this.verify = verify;
        return this;
    }

    public int getLogistics_number() {
        return logistics_number;
    }

    public LogisticsInfo setLogistics_number(int logistics_number) {
        this.logistics_number = logistics_number;
        return this;
    }

    public String getLogistics_way() {
        return logistics_way;
    }

    public LogisticsInfo setLogistics_way(String logistics_way) {
        this.logistics_way = logistics_way;
        return this;
    }

    public String getLogistics_way_number() {
        return logistics_way_number;
    }

    public LogisticsInfo setLogistics_way_number(String logistics_way_number) {
        this.logistics_way_number = logistics_way_number;
        return this;
    }

    public String getMailing_type() {
        return mailing_type;
    }

    public LogisticsInfo setMailing_type(String mailing_type) {
        this.mailing_type = mailing_type;
        return this;
    }

    public String getMailing_phone() {
        return mailing_phone;
    }

    public LogisticsInfo setMailing_phone(String mailing_phone) {
        this.mailing_phone = mailing_phone;
        return this;
    }

    public String getMailing_address() {
        return mailing_address;
    }

    public LogisticsInfo setMailing_address(String mailing_address) {
        this.mailing_address = mailing_address;
        return this;
    }

    public String getShipping_phone() {
        return shipping_phone;
    }

    public LogisticsInfo setShipping_phone(String shipping_phone) {
        this.shipping_phone = shipping_phone;
        return this;
    }

    public String getShipping_address() {
        return shipping_address;
    }

    public LogisticsInfo setShipping_address(String shipping_address) {
        this.shipping_address = shipping_address;
        return this;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public LogisticsInfo setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
        return this;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public LogisticsInfo setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public String getMailing_name() {
        return mailing_name;
    }

    public LogisticsInfo setMailing_name(String mailing_name) {
        this.mailing_name = mailing_name;
        return this;
    }

    public String getShipping_name() {
        return shipping_name;
    }

    public LogisticsInfo setShipping_name(String shipping_name) {
        this.shipping_name = shipping_name;
        return this;
    }

    public String getNote() {
        return note;
    }

    public LogisticsInfo setNote(String note) {
        this.note = note;
        return this;
    }

    public Timestamp getShipping_time() {
        return shipping_time;
    }

    public LogisticsInfo setShipping_time(Timestamp shipping_time) {
        this.shipping_time = shipping_time;
        return this;
    }
}
