package org.example.patterns.strategy.example2.strtegies;

import static org.example.patterns.strategy.example2.Constants.SWORD_BEHAVIOR;

public class SwordBehavior implements WeaponBehavior {
    @Override
    public void useWeapon() {
        System.out.println(SWORD_BEHAVIOR);
    }
}
