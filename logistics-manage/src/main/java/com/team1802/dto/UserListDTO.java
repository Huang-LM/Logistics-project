package com.team1802.dto;

/**
 * @author fengmingxian
 * Created on 2020-12-27
 */
public class UserListDTO {

    private int page;

    private int pageSize;


    public int getPage() {
        return page;
    }

    public UserListDTO setPage(int page) {
        this.page = page;
        return this;
    }

    public int getPageSize() {
        return pageSize;
    }

    public UserListDTO setPageSize(int pageSize) {
        this.pageSize = pageSize;
        return this;
    }


}
