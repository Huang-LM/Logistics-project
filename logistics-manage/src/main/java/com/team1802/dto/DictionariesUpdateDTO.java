package com.team1802.dto;

public class DictionariesUpdateDTO {
    private  int id;

    private String name;

    private int parentId;

    public int getId() {
        return id;
    }

    public DictionariesUpdateDTO setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public DictionariesUpdateDTO setName(String name) {
        this.name = name;
        return this;
    }

    public int getParentId() {
        return parentId;
    }

    public DictionariesUpdateDTO setParentId(int parentId) {
        this.parentId = parentId;
        return this;
    }
}
