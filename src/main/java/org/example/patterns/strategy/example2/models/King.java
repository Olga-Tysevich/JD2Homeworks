package org.example.patterns.strategy.example2.models;

import org.example.patterns.strategy.example2.strtegies.WeaponBehavior;

public class King extends Character{
    public King(String name, WeaponBehavior weapon) {
        super(name, weapon);
    }

    public King(String name) {
        super(name);
    }
}
