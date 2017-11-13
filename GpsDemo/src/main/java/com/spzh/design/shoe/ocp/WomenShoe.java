package com.spzh.design.shoe.ocp;

public class WomenShoe implements Shoe {
    private String name;
    private String type;
    private int value;

    public String getName() {
        return this.name;
    }
    public String getType() {
        return this.type;
    }
    public int getValue() {
        return this.value;
    }

    public WomenShoe(String name, String type, int value) {
        this.name = name;
        this.type = type;
        this.value = value;
    }

    public WomenShoe() {
    }
}
