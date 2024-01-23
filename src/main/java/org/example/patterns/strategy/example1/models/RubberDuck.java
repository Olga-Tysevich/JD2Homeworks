package org.example.patterns.strategy.example1.models;

import org.example.patterns.strategy.example1.strategies.fly.FlyNoWay;
import org.example.patterns.strategy.example1.strategies.quack.Squeak;

import static org.example.patterns.strategy.example1.Constants.RUBBER_DUCK_DISPLAY;

public class RubberDuck extends Duck{

    public RubberDuck() {
        super(new FlyNoWay(),new Squeak());
    }

    @Override
    public void display() {
        System.out.println(RUBBER_DUCK_DISPLAY);
    }
}
