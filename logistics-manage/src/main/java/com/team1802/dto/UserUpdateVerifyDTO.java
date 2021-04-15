package com.team1802.dto;

public class UserUpdateVerifyDTO {

    private Integer id;
    
    private Integer verify;

    public Integer getId() {
        return id;
    }

    public UserUpdateVerifyDTO setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getVerify() {
        return verify;
    }

    public UserUpdateVerifyDTO setVerify(Integer verify) {
        this.verify = verify;
        return this;
    }
}
