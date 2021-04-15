package com.team1802.vo;

import java.util.List;

/**
 * @author fengmingxian
 * Created on 2020-12-27
 */
public class ListVO<T> {
    private int page;
    private int pageSize;
    private List<T> list;
    private int total;

    public int getPage() {
        return page;
    }

    public ListVO<T> setPage(int page) {
        this.page = page;
        return this;
    }

    public int getPageSize() {
        return pageSize;
    }

    public ListVO<T> setPageSize(int pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public List<T> getList() {
        return list;
    }

    public ListVO<T> setList(List<T> list) {
        this.list = list;
        return this;
    }

    public int getTotal() {
        return total;
    }

    public ListVO<T> setTotal(int total) {
        this.total = total;
        return this;
    }
}
