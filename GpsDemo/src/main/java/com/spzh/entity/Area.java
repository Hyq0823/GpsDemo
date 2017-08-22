package com.spzh.entity;

/**
 * Created by hyq on 2017/8/22.
 */
public class Area {
    private String id;
    private String name;
    private String hasChild;//是否有子节点
    private String parentId;
    private String code; 	// 区域编码
    //.....


    public Area(String id, String name, String hasChild, String parentId, String code) {
        this.id = id;
        this.name = name;
        this.hasChild = hasChild;
        this.parentId = parentId;
        this.code = code;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHasChild() {
        return hasChild;
    }

    public void setHasChild(String hasChild) {
        this.hasChild = hasChild;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
