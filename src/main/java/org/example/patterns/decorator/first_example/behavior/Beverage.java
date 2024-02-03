package org.example.patterns.decorator.first_example.behavior;

import org.example.patterns.decorator.first_example.prices.Size;

public abstract class Beverage {
    private String name;
    private double price;
    Size size = Size.TALL;

    public Beverage(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getDescription() {
        return name + size.getSizeName();
    }

    public double cost() {
        return price;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public Size getSize() {
        return this.size;
    }

    public String getName() {
        return name;
    }

}
