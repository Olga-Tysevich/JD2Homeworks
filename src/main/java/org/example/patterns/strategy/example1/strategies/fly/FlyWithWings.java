package org.example.patterns.strategy.example1.strategies.fly;

import static org.example.patterns.strategy.example1.Constants.FLY_WITH_WINGS_BEHAVIOR;

public class FlyWithWings implements FlyBehavior{
    @Override
    public void fly() {
        System.out.println(FLY_WITH_WINGS_BEHAVIOR);
    }
}
