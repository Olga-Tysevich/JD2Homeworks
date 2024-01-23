package org.example.patterns.strategy.example1.models;

import org.example.patterns.strategy.example1.strategies.fly.FlyNoWay;
import org.example.patterns.strategy.example1.strategies.quack.Quack;

import static org.example.patterns.strategy.example1.Constants.MODEL_DUCK_DISPLAY;

public class ModelDuck extends Duck {
    public ModelDuck() {
        super(new FlyNoWay(), new Quack());
    }
    public void display() {
        System.out.println(MODEL_DUCK_DISPLAY);
    }
}
