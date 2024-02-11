package org.example.patterns.decorator.first_example.models.condiments;

import org.example.patterns.decorator.first_example.behavior.Beverage;
import org.example.patterns.decorator.first_example.behavior.CondimentDecorator;
import org.example.patterns.decorator.first_example.prices.CondimentPrice;

public class Soy extends CondimentDecorator {

    public Soy(Beverage beverage) {
        super(beverage, CondimentPrice.SOY.getName(), CondimentPrice.SOY.getPrice());
    }
}
