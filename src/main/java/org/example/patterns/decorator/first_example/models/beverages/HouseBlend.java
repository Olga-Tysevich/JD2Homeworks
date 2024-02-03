package org.example.patterns.decorator.first_example.models.beverages;

import org.example.patterns.decorator.first_example.behavior.Beverage;
import org.example.patterns.decorator.first_example.prices.CoffeePrice;

public class HouseBlend extends Beverage {
    public HouseBlend() {
        super(CoffeePrice.HOUSE_BLEND.getName(), CoffeePrice.HOUSE_BLEND.getPrice());
    }
}
