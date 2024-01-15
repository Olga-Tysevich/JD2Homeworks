package org.example.lesson2.task1scientists;


import org.example.lesson2.task1scientists.models.Scientist;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Constants {
    //competition
    public static final int INITIAL_NUMBER_OF_PARTS = 20;
    public static final String SCIENTIST_NAME = "Scientist №";
    public static final int NUMBER_OF_SCIENTIST = 2;
    public static final String ERROR_MESSAGE = "Too few competitors";
    public static final Predicate<List<Scientist>> HAS_ONE_WINNER = winners -> winners.size() == 1;
    public static final Predicate<List<Scientist>> HAS_MANY_WINNER = winners -> winners.size() != 0;
    public static final Consumer<Scientist> PRINT_NUMBER_OF_ROBOTS =
            scientist -> System.out.println("Number of robots a " + scientist.getName() + " has: " + scientist.getNumberOfRobots());
    public static final Consumer<List<Scientist>> PRINT_ONE_WINNER =
            winners -> System.out.println("Winner: " + winners.get(0).getName());
    public static final Consumer<List<Scientist>> PRINT_GAME_DRAW = scientists -> System.out.println("Game draw!");
    public static final Consumer<List<Scientist>> PRINT_ALL_WINNERS  =
            winners -> {
                System.out.println("Winners:");
                winners.forEach(w -> System.out.println(w.getName()));
            };
    public static final String NOBODY_WON_MESSAGE = "Nobody won!";

    //factory
    public static final int FACTORY_MIN_NUMBER_OF_PARTS = 1;
    public static final int FACTORY_MAX_NUMBER_OF_PARTS = 4;

    //servant
    public static final int SERVANT_MIN_NUMBER_OF_PARTS = 1;
    public static final int SERVANT_MAX_NUMBER_OF_PARTS = 4;


    //common
    public static final int NUMBER_OF_NIGHTS = 100;
    public static final int DAY_LENGTH = 100;


}
