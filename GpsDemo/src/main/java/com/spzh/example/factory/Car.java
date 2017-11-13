package com.spzh.example.factory;

//抽象产品
public abstract   class Car {
    CarType model;
    public Car(CarType model) {
        this.model = model;
    }
    public Car() {
    }

    public CarType getModel() {
        return model;
    }

    public void setModel(CarType model) {
        this.model = model;
    }
}
