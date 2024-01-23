package org.example.patterns.strategy.example1.strategies.quack;

import static org.example.patterns.strategy.example1.Constants.MUTE_QUACK_BEHAVIOR;

public class MuteQuack implements QuackBehavior{
    @Override
    public void quack() {
        System.out.println(MUTE_QUACK_BEHAVIOR);
    }
}
