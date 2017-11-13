package com.spzh.design.shoe.ocp;

/**
 * Created by hyq on 2017/10/10.
 * womenshow >200 7.5折， >150 8.5折，其他8.5折
 */
public class CutWomenShoe extends  WomenShoe {
    @Override
    public int getValue() {
        int price = super.getValue();
        if(price > 200){
            return price * 75 / 100;
        }
        if(price > 150){
            return price * 80 / 100;
        }
        return price * 85 / 100;
    }

    public CutWomenShoe(String name, String type, int value) {
        super(name, type, value);
    }
}
