package org.example.patterns.decorator.first_example.behavior;


public abstract class CondimentDecorator extends Beverage {
    private Beverage beverage;

    public CondimentDecorator(Beverage beverage, String name, double price) {
        super(name, price);
        this.beverage = beverage;
        setSize(beverage.getSize());
    }

    @Override
    public double cost() {
        return super.cost() * super.getSize().getPriceFactor() + beverage.cost();
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + super.getName();
    }

}
