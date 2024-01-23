package org.example.patterns.strategy.example2.models;

import org.example.patterns.strategy.example2.strtegies.WeaponBehavior;

public class Queen extends Character {
    public Queen(String name, WeaponBehavior weapon) {
        super(name, weapon);
    }

    public Queen(String name) {
        super(name);
    }
}
