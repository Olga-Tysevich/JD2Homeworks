package org.example.patterns.strategy.example2.models;

import org.example.patterns.strategy.example2.strtegies.WeaponBehavior;

public class Knight extends Character {
    public Knight(String name, WeaponBehavior weapon) {
        super(name, weapon);
    }

    public Knight(String name) {
        super(name);
    }
}
