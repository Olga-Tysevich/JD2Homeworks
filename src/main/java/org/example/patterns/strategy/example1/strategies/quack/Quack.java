package org.example.patterns.strategy.example1.strategies.quack;

import static org.example.patterns.strategy.example1.Constants.QUACK_BEHAVIOR;

public class Quack implements QuackBehavior{
    @Override
    public void quack() {
        System.out.println(QUACK_BEHAVIOR);
    }
}
