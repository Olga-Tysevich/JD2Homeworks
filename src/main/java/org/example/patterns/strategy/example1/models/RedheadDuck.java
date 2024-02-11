package org.example.patterns.strategy.example1.models;

import org.example.patterns.strategy.example1.strategies.fly.FlyWithWings;
import org.example.patterns.strategy.example1.strategies.quack.Quack;

import static org.example.patterns.strategy.example1.Constants.REDHEAD_DUCK_DISPLAY;

public class RedheadDuck extends Duck{

    public RedheadDuck() {
        super(new FlyWithWings(),new Quack());
    }

    @Override
    public void display() {
        System.out.println(REDHEAD_DUCK_DISPLAY);
    }
}
