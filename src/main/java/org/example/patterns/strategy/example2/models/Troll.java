package org.example.patterns.strategy.example2.models;

import org.example.patterns.strategy.example2.strtegies.WeaponBehavior;

public class Troll extends Character{
    public Troll(String name, WeaponBehavior weapon) {
        super(name, weapon);
    }

    public Troll(String name) {
        super(name);
    }
}
