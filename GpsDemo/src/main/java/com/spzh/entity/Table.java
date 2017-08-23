package com.spzh.entity;

/**
 * Created by Administrator on 2017/8/21.
 */
public class Table {
    private String collect; //收集物
    private String type;
    private String unit;
    private String weight_only;
    private String weight_left;
    private String weight_right;
    private String weight_min;
    private String weight_max;
    private String charge;
    private String gover;
    private String payback;

    @Override
    public String toString() {
        return "Table{" +
                "收集物='" + collect + '\'' +
                ", 类型='" + type + '\'' +
                ", 单位='" + unit + '\'' +
                ", 计量='" + weight_only + '\'' +
                ", 计量（小）='" + weight_left + '\'' +
                ", 计量（大）='" + weight_right + '\'' +
                ", 重量（小）='" + weight_min + '\'' +
                ", 重量（大）='" + weight_max + '\'' +
                ", 收费='" + charge + '\'' +
                ", 政府补贴='" + gover + '\'' +
                ", 返还='" + payback + '\'' +
                '}';
    }

    public Table() {
    }

    public String getCollect() {
        return collect;
    }

    public void setCollect(String collect) {
        this.collect = collect;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getWeight_only() {
        return weight_only;
    }

    public void setWeight_only(String weight_only) {
        this.weight_only = weight_only;
    }

    public String getWeight_left() {
        return weight_left;
    }

    public void setWeight_left(String weight_left) {
        this.weight_left = weight_left;
    }

    public String getWeight_right() {
        return weight_right;
    }

    public void setWeight_right(String weight_right) {
        this.weight_right = weight_right;
    }

    public String getWeight_min() {
        return weight_min;
    }

    public void setWeight_min(String weight_min) {
        this.weight_min = weight_min;
    }

    public String getWeight_max() {
        return weight_max;
    }

    public void setWeight_max(String weight_max) {
        this.weight_max = weight_max;
    }

    public String getCharge() {
        return charge;
    }

    public void setCharge(String charge) {
        this.charge = charge;
    }

    public String getGover() {
        return gover;
    }

    public void setGover(String gover) {
        this.gover = gover;
    }

    public String getPayback() {
        return payback;
    }

    public void setPayback(String payback) {
        this.payback = payback;
    }
}
