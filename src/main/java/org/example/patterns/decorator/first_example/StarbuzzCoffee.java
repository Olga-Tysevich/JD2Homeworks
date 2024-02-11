package org.example.patterns.decorator.first_example;

import org.example.patterns.decorator.first_example.behavior.Beverage;
import org.example.patterns.decorator.first_example.models.beverages.DarkRoast;
import org.example.patterns.decorator.first_example.models.beverages.Espresso;
import org.example.patterns.decorator.first_example.models.beverages.HouseBlend;
import org.example.patterns.decorator.first_example.models.condiments.Mocha;
import org.example.patterns.decorator.first_example.models.condiments.Soy;
import org.example.patterns.decorator.first_example.models.condiments.Whip;
import org.example.patterns.decorator.first_example.prices.Size;

public class StarbuzzCoffee {
    private static final String CURRENCY = "$ ";

    public static void main(String[] args) {
        Beverage beverage = new Espresso();
        System.out.println(beverage.getDescription() + " " + String.format("%.2f", beverage.cost()) + CURRENCY);
        Beverage beverage2 = new DarkRoast();
        beverage2 = new Mocha(beverage2);
        beverage2 = new Mocha(beverage2);
        beverage2 = new Whip(beverage2);
        System.out.println(beverage2.getDescription() + " " + String.format("%.2f", beverage2.cost()) + CURRENCY);
        Beverage beverage3 = new HouseBlend();
        beverage3 = new Soy(beverage3);
        beverage3 = new Mocha(beverage3);
        beverage3 = new Whip(beverage3);
        System.out.println(beverage3.getDescription() + " " + String.format("%.2f", beverage3.cost()) + CURRENCY);
        Beverage beverage4 = new HouseBlend();
        beverage4.setSize(Size.GRANDE);
        beverage4 = new Soy(beverage4);
        beverage4 = new Mocha(beverage4);
        beverage4 = new Whip(beverage4);
        System.out.println(beverage4.getDescription() + " " + String.format("%.2f", beverage4.cost()) + CURRENCY);
    }
}
