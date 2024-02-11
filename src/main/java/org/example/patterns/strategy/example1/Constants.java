package org.example.patterns.strategy.example1;

public class Constants {

    //models.Duck
    public static final String DUCK_DISPLAY = "I’m a duck";
    public static final String SWIM_BEHAVIOR = "All ducks float, even decoys!";
    //models.MallardDuck
    public static final String MALLARD_DUCK_DISPLAY = "I’m a real Mallard duck";
    //models.DecoyDuck
    public static final String DECOY_DUCK_DISPLAY = "I’m a real Decoy duck";
    //models.RedheadDuck
    public static final String REDHEAD_DUCK_DISPLAY = "I’m a real Redhead duck";
    //models.RubberDuck
    public static final String RUBBER_DUCK_DISPLAY = "I’m a real Rubber duck";
    //models.ModelDuck
    public static final String MODEL_DUCK_DISPLAY = "I’m a real Model duck";


    //behavior.fly.FlyWithWings
    public static final String FLY_WITH_WINGS_BEHAVIOR = "I’m flying!!";
    //behavior.fly.FlyNoWay
    public static final String FLY_NO_WAY_BEHAVIOR = "I can’t fly";
    //behavior.fly.FlyRocketPowered
    public static final String FLY_ROCKET_POWERED_BEHAVIOR = "I’m flying with a rocket!";
    //behavior.quack.Quack
    public static final String QUACK_BEHAVIOR = "Quack";
    //behavior.quack.Squeak
    public static final String SQUEAK_BEHAVIOR = "Squeak";
    //behavior.quack.MuteQuack
    public static final String MUTE_QUACK_BEHAVIOR = "<< Silence >>";


    private Constants() {
    }
}
