package org.example.patterns.strategy.example1.strategies.quack;

import static org.example.patterns.strategy.example1.Constants.SQUEAK_BEHAVIOR;

public class Squeak implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println(SQUEAK_BEHAVIOR);
    }
}
