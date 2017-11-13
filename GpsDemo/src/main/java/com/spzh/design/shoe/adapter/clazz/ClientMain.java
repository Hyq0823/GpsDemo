package com.spzh.design.shoe.adapter.clazz;

/**
 * Created by hyq on 2017/10/10.
 */
public class ClientMain {
    public static void main(String[] args){
        //普通目标实现类
        OriginalImpl common = new OriginalImpl();
        common.handler();

        //适配器目标实现类
        Adapter adapter = new Adapter();
        adapter.handler();
    }



}
