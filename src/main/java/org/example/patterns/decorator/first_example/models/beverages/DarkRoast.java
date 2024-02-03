package org.example.patterns.decorator.first_example.models.beverages;

import org.example.patterns.decorator.first_example.behavior.Beverage;
import org.example.patterns.decorator.first_example.prices.CoffeePrice;

public class DarkRoast extends Beverage {

    public DarkRoast() {
        super(CoffeePrice.DARK_ROAST.getName(), CoffeePrice.DARK_ROAST.getPrice());
    }


    @Override
    public double cost() {
        return super.cost() * getSize().getPriceFactor();
    }
}
