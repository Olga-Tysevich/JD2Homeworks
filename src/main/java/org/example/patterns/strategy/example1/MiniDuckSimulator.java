package org.example.patterns.strategy.example1;

import org.example.patterns.strategy.example1.strategies.fly.FlyRocketPowered;
import org.example.patterns.strategy.example1.models.Duck;
import org.example.patterns.strategy.example1.models.MallardDuck;
import org.example.patterns.strategy.example1.models.ModelDuck;

public class MiniDuckSimulator {
    public static void main(String[] args) {
        Duck mallard = new MallardDuck();
        mallard.performQuack();
        mallard.performFly();

        Duck model = new ModelDuck();
        model.performFly();
        model.setFlyBehavior(new FlyRocketPowered());
        model.performFly();
    }
}
