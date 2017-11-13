package com.spzh.design.shoe.ocp;

/**
 * Created by hyq on 2017/10/10.
 *
 * menshoe >200 8折，>150 8.5折，其他9折
 * womenshow >200 7.5折， >150 8.5折，其他8.5折
 *
 * shoe和 打折的shoe
 */
public interface Shoe {
    public String getName();
    public String getType();
    public int getValue();
}
