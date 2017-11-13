package com.spzh.design.shoe.singleton;

/**
 * Created by hyq on 2017/10/10.
 * 饿汉
 */
public class SingletonHungry {
    private  SingletonHungry(){}
    //错误点一: 创建实例的时候不加入static，则此成员为所有的对象而共享。造成了循环依赖的情况
    private static SingletonHungry instance = new SingletonHungry();

    //错误点二： 提供给外部访问的方法，不应该是对象级别的方法,由于构造函数私有化的原因，外部根本无法创建对象。
    public static SingletonHungry getInstance(){
        return instance;
    }
}
