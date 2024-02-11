package org.example.patterns.strategy.example1.strategies.fly;

import static org.example.patterns.strategy.example1.Constants.FLY_ROCKET_POWERED_BEHAVIOR;

public class FlyRocketPowered implements FlyBehavior{
    @Override
    public void fly() {
        System.out.println(FLY_ROCKET_POWERED_BEHAVIOR);
    }
}
