package org.example.patterns.decorator.first_example.models.condiments;

import org.example.patterns.decorator.first_example.behavior.Beverage;
import org.example.patterns.decorator.first_example.behavior.CondimentDecorator;
import org.example.patterns.decorator.first_example.prices.CondimentPrice;

public class Whip extends CondimentDecorator {

    public Whip(Beverage beverage) {
        super(beverage, CondimentPrice.WHIP.getName(), CondimentPrice.WHIP.getPrice());
    }
}
