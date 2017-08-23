package com.spzh.excel.form;

import com.spzh.excel.annotation.ExcelField;

/**
 * Created by hyq on 2017/8/22.
 */
public class AreaForm {
    @ExcelField(title = "地区名称")
    private String name;
    @ExcelField(title = "地区id")
    private String id;
    @ExcelField(title="是否有下级")
    private String hasChild;
    @ExcelField(title="地区父级id")
    private String parentId;
    @ExcelField(title = "地区编码")
    private String code;


    public AreaForm(String id, String name, String hasChild, String parentId, String code) {
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
