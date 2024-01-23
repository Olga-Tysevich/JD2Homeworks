package org.example.patterns.strategy.example1.models;

import org.example.patterns.strategy.example1.strategies.fly.FlyWithWings;
import org.example.patterns.strategy.example1.strategies.quack.Quack;

import static org.example.patterns.strategy.example1.Constants.MALLARD_DUCK_DISPLAY;

public class MallardDuck extends Duck {

    public MallardDuck() {
        super(new FlyWithWings(), new Quack());
    }

    @Override
    public void display() {
        System.out.println(MALLARD_DUCK_DISPLAY);
    }
}
