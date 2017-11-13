package com.spzh.design.shoe.adapter.clazz;

/**
 * Created by hyq on 2017/10/10.
 * 适配者（.....类适配模式...）
 */
public class Adapter extends  Adaptee implements Target {
    @Override
    public void handler() {
        System.out.println("adapter使用usb3.0传输...");
    }
}
