package com.spzh.design.shoe.adapter.object;

import com.spzh.design.shoe.adapter.clazz.Target;

/**
 * Created by hyq on 2017/10/10.
 * 普通目标类，只是提供普通功能
 */
public class OriginalImpl implements Target{
    @Override
    public void handler() {
        System.out.println("usb2.0传输。。。。。");
    }
}
