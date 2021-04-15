package com.team1802.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;

public class LogisticsSelectDTO {
    private Integer id;

    private Integer verify;

    private Integer logistics_number;

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

    public Integer getId() {
        return id;
    }

    public LogisticsSelectDTO setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getVerify() {
        return verify;
    }

    public LogisticsSelectDTO setVerify(Integer verify) {
        this.verify = verify;
        return this;
    }

    public Integer getLogistics_number() {
        return logistics_number;
    }

    public LogisticsSelectDTO setLogistics_number(Integer logistics_number) {
        this.logistics_number = logistics_number;
        return this;
    }

    public String getLogistics_way() {
        return logistics_way;
    }

    public LogisticsSelectDTO setLogistics_way(String logistics_way) {
        this.logistics_way = logistics_way;
        return this;
    }

    public String getLogistics_way_number() {
        return logistics_way_number;
    }

    public LogisticsSelectDTO setLogistics_way_number(String logistics_way_number) {
        this.logistics_way_number = logistics_way_number;
        return this;
    }

    public String getMailing_type() {
        return mailing_type;
    }

    public LogisticsSelectDTO setMailing_type(String mailing_type) {
        this.mailing_type = mailing_type;
        return this;
    }

    public String getMailing_name() {
        return mailing_name;
    }

    public LogisticsSelectDTO setMailing_name(String mailing_name) {
        this.mailing_name = mailing_name;
        return this;
    }

    public String getMailing_phone() {
        return mailing_phone;
    }

    public LogisticsSelectDTO setMailing_phone(String mailing_phone) {
        this.mailing_phone = mailing_phone;
        return this;
    }

    public String getMailing_address() {
        return mailing_address;
    }

    public LogisticsSelectDTO setMailing_address(String mailing_address) {
        this.mailing_address = mailing_address;
        return this;
    }

    public String getShipping_name() {
        return shipping_name;
    }

    public LogisticsSelectDTO setShipping_name(String shipping_name) {
        this.shipping_name = shipping_name;
        return this;
    }

    public String getShipping_phone() {
        return shipping_phone;
    }

    public LogisticsSelectDTO setShipping_phone(String shipping_phone) {
        this.shipping_phone = shipping_phone;
        return this;
    }

    public String getShipping_address() {
        return shipping_address;
    }

    public LogisticsSelectDTO setShipping_address(String shipping_address) {
        this.shipping_address = shipping_address;
        return this;
    }

    public String getNote() {
        return note;
    }

    public LogisticsSelectDTO setNote(String note) {
        this.note = note;
        return this;
    }

    public Timestamp getShipping_time() {
        return shipping_time;
    }

    public LogisticsSelectDTO setShipping_time(Timestamp shipping_time) {
        this.shipping_time = shipping_time;
        return this;
    }
}
