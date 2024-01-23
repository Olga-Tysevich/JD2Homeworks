package org.example.patterns.strategy.example1.models;

import org.example.patterns.strategy.example1.strategies.quack.QuackBehavior;
import org.example.patterns.strategy.example1.strategies.fly.FlyBehavior;

import static org.example.patterns.strategy.example1.Constants.DUCK_DISPLAY;
import static org.example.patterns.strategy.example1.Constants.SWIM_BEHAVIOR;

public abstract class Duck {
    private FlyBehavior flyBehavior;
    private QuackBehavior quackBehavior;

    public Duck(FlyBehavior flyBehavior, QuackBehavior quackBehavior) {
        this.flyBehavior = flyBehavior;
        this.quackBehavior = quackBehavior;
    }

    public Duck() {
    }

    public void swim() {
        System.out.println(SWIM_BEHAVIOR);
    }

    public void display(){
        System.out.println(DUCK_DISPLAY);
    }

    public void performQuack() {
        quackBehavior.quack();
    }

    public void performFly() {
        flyBehavior.fly();
    }

    public void setFlyBehavior(FlyBehavior flyBehavior) {
        this.flyBehavior = flyBehavior;
    }

    public void setQuackBehavior(QuackBehavior quackBehavior) {
        this.quackBehavior = quackBehavior;
    }
}
