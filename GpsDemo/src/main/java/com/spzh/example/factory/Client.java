package com.spzh.example.factory;

public class Client {

    public static void main(String[] args){
        //简单工厂通过不同的车辆类型进行车辆的生产。
        //缺点是需要额外构造一个工厂来产生车辆。加大了开销
        CarFactory carFactory = new CarFactory();
        Car car = carFactory.buildCar(CarType.SMALL);
        Car car1 = carFactory.buildCar(CarType.MODDLE);
        Car car2 = carFactory.buildCar(CarType.LARGE);
       // CarType model = car.getModel();

        System.out.println(car.getModel());
        System.out.println(car1.getModel());
        System.out.println(car2.getModel());
    }
}
