package com.team1802.pojo;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

public class Node {

    @JSONField(ordinal = 3)
    private Integer id;

    @JSONField(ordinal = 1)
    private String name;

    @JSONField(serialize = false)
    private Integer parentId;

    @JSONField(ordinal = 4)
    private String icon;

    @JSONField(ordinal = 2)
    private String url;

    @JSONField(ordinal = 5)
    private List<Node> children;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Node> getChildren() {
        return children;
    }

    public void setChildren(List<Node> children) {
        this.children = children;
    }

    public String getIcon() {
        return icon;
    }

    public Node setIcon(String icon) {
        this.icon = icon;
        return this;
    }
}
