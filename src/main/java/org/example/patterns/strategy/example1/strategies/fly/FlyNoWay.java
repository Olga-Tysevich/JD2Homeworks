package org.example.patterns.strategy.example1.strategies.fly;

import static org.example.patterns.strategy.example1.Constants.FLY_NO_WAY_BEHAVIOR;

public class FlyNoWay implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println(FLY_NO_WAY_BEHAVIOR);
    }
}
