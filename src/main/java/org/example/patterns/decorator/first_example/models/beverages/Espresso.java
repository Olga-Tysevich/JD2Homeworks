package org.example.patterns.decorator.first_example.models.beverages;

import org.example.patterns.decorator.first_example.behavior.Beverage;
import org.example.patterns.decorator.first_example.prices.CoffeePrice;

public class Espresso extends Beverage {
    public Espresso() {
        super(CoffeePrice.ESPRESSO.getName(), CoffeePrice.ESPRESSO.getPrice());
    }

}
