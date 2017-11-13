package com.spzh.design.shoe.singleton;

/**
 * Created by hyq on 2017/10/10.
 * 懒汉式
 *
 * 在多线程环境下还是会存在问题
 */
public class SingletonLazy {
    private SingletonLazy(){}

    private  static SingletonLazy instance = null;
    synchronized public SingletonLazy getInstance(){
        //线程2此时拿到的instance为null
        if(instance == null){
            //线程1 进入,但是尚未赋值。
            instance = new SingletonLazy();
            //线程3 已经赋值，instance不为空
        }
        return instance;
    }

}
