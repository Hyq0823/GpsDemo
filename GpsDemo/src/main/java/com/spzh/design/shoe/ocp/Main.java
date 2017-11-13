package com.spzh.design.shoe.ocp;

/**
 * Created by hyq on 2017/10/10.
 * menshoe >200 8折，>150 8.5折，其他9折
 * womenshow >200 7.5折， >150 8.5折，其他8.5折
 *
 *      开闭原则
 *
 * 开： 程序应当对扩展开发。
 * 闭： 程序应该对修改关闭。
 * 针对需求变更时，尽量不修改原有的类， 使用接口和抽象类的情况下来实现功能。
 * 而是通过继承/组合的方式进行扩展
 * 在不变动原有类的情况下，实现新的 功能需求。
 *
 */
public class Main {
    //男 打折后：240
    //女 打折后: 750
    public  static void main(String[] args){
        CutMenShoe cutMenShoe = new CutMenShoe("男xie","men",300);
        System.out.println("男 打折后："+cutMenShoe.getValue());

        CutWomenShoe cutWomenShoe = new CutWomenShoe("女xie","woman",1000);
        System.out.println("女 打折后: "+cutWomenShoe.getValue());

    }
}
