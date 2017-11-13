package com.spzh.design.shoe.ocp;

/**
 * Created by hyq on 2017/10/10.
 * //menshoe >200 8折，>150 8.5折，其他9折
 */
public class CutMenShoe extends  MenShoe {
    @Override
    public int getValue() {
        int price = super.getValue();
        if(price > 200){
            return  price*80/100;
        }
        if(price > 150){
            return price*85/100;
        }
        return price*90/100;
    }

    public CutMenShoe(String name, String type, int value) {
        super(name, type, value);
    }
}
