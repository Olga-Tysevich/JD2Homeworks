package org.example.patterns.strategy.example2.models;

import org.example.patterns.strategy.example2.strtegies.WeaponBehavior;

public abstract class Character {
    private String name;
    private WeaponBehavior weapon;

    public Character(String name, WeaponBehavior weapon) {
        this.name = name;
        this.weapon = weapon;
    }

    public Character(String name) {
        this.name = name;
    }

    public void setWeapon(WeaponBehavior weapon) {
        this.weapon = weapon;
    }
}
