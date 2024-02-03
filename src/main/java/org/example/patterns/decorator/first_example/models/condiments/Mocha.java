package org.example.patterns.decorator.first_example.models.condiments;

import org.example.patterns.decorator.first_example.behavior.Beverage;
import org.example.patterns.decorator.first_example.behavior.CondimentDecorator;
import org.example.patterns.decorator.first_example.prices.CondimentPrice;

public class Mocha extends CondimentDecorator {

    public Mocha(Beverage beverage) {
        super(beverage, CondimentPrice.MOCHA.getName(), CondimentPrice.MOCHA.getPrice());
    }
}
