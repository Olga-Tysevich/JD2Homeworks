package org.example.patterns.strategy.example2.strtegies;

import static org.example.patterns.strategy.example2.Constants.KNIFE_BEHAVIOR;

public class KnifeBehavior implements WeaponBehavior{
    @Override
    public void useWeapon() {
        System.out.println(KNIFE_BEHAVIOR);
    }
}
