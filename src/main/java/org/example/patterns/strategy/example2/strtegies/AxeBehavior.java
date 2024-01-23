package org.example.patterns.strategy.example2.strtegies;

import static org.example.patterns.strategy.example2.Constants.AXE_BEHAVIOR;

public class AxeBehavior implements WeaponBehavior {
    @Override
    public void useWeapon() {
        System.out.println(AXE_BEHAVIOR);
    }
}
