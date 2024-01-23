package org.example.patterns.strategy.example2.strtegies;

import static org.example.patterns.strategy.example2.Constants.BOW_AND_ARROW_BEHAVIOR;

public class BowAndArrowBehavior implements  WeaponBehavior{
    @Override
    public void useWeapon() {
        System.out.println(BOW_AND_ARROW_BEHAVIOR);
    }
}
