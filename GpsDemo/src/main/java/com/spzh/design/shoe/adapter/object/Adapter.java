package com.spzh.design.shoe.adapter.object;

import com.spzh.design.shoe.adapter.clazz.Target;

/**
 * Created by hyq on 2017/10/10.
 */
public class Adapter implements Target {
    private Adaptee adaptee;
    public Adapter(Adaptee adaptee){
        this.adaptee = adaptee;
    }

    @Override
    public void handler() {
        adaptee.run();
    }
}
