package com.spzh.example.factory;

/**
 * Created by hyq on 2017/10/11.
 */
public class SmallCar extends  Car {
    public SmallCar(CarType model) {
        super(CarType.SMALL);
    }
    public SmallCar() {
        super(CarType.SMALL);
    }
}
