package com.team1802.dto;

public class LogisticsListDTO {

    private int page;

    private int pageSize;

    public int getPage() {
        return page;
    }

    public LogisticsListDTO setPage(int page) {
        this.page = page;
        return this;
    }

    public int getPageSize() {
        return pageSize;
    }

    public LogisticsListDTO setPageSize(int pageSize) {
        this.pageSize = pageSize;
        return this;
    }
}
