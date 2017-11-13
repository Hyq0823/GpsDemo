package com.spzh.example.factory;

/**
 * Created by hyq on 2017/10/11.
 * 简单工厂--构造不同类型的车型
 */
public class  CarFactory {
    public Car buildCar(CarType type){
        if(CarType.SMALL.equals(type)){
            return new SmallCar();
        }
        if(CarType.MODDLE.equals(type)){
            return new MiddleCar();
        }
        if(CarType.LARGE.equals(type)){
            return new LargeCar();
        }
        return new SmallCar();
    }

}
