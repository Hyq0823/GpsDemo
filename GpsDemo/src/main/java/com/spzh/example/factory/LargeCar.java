package com.spzh.example.factory;

/**
 * Created by hyq on 2017/10/11.
 */
public class LargeCar extends   Car {

    public LargeCar(CarType model) {
        super(CarType.LARGE);
    }
    public LargeCar() {
        super(CarType.LARGE);
    }
}
