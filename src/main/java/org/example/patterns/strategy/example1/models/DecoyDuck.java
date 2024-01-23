package org.example.patterns.strategy.example1.models;

import org.example.patterns.strategy.example1.strategies.fly.FlyNoWay;
import org.example.patterns.strategy.example1.strategies.quack.MuteQuack;

import static org.example.patterns.strategy.example1.Constants.DECOY_DUCK_DISPLAY;

public class DecoyDuck extends Duck{

    public DecoyDuck() {
        super(new FlyNoWay(),new MuteQuack());
    }

    @Override
    public void display() {
        System.out.println(DECOY_DUCK_DISPLAY);
    }

}
