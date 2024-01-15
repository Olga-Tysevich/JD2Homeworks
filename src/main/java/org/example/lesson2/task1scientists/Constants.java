package org.example.lesson2.task1scientists;

import org.example.lesson2.task1scientists.models.Scientist;
import java.util.List;
import java.util.function.Predicate;

public class Constants {
    //competition
    public static final int INITIAL_NUMBER_OF_PARTS = 20;
    public static final String SCIENTIST_NAME = "Scientist №";
    public static final int NUMBER_OF_SCIENTIST = 2;
    public static final String ERROR_MESSAGE = "Too few competitors";
    public static final Predicate<List<Scientist>> HAS_ONE_WINNER = winners -> winners.size() == 1;
    public static final Predicate<List<Scientist>> HAS_MANY_WINNER = winners -> winners.size() != 0;
    public static final String NUMBER_OF_ROBOTS_MESSAGE = "%s has %d robots\n";
    public static final String ONE_WINNER_MESSAGE = "Winner: %s\n";
    public static final int WINNER = 0;
    public static final String GAME_DRAW_MESSAGE = "Game draw!\n";
    public static final String MANY_WINNERS_MESSAGE = "Winners:\n ";
    public static final String NOBODY_WON_MESSAGE = "Nobody won!";

    //factory
    public static final int FACTORY_MIN_NUMBER_OF_PARTS = 1;
    public static final int FACTORY_MAX_NUMBER_OF_PARTS = 4;
    public static final String FACTORY_WORK = "Day %d, factory put %d robot parts\n";

    //scientist
    public static final int SERVANT_MIN_NUMBER_OF_PARTS = 1;
    public static final int SERVANT_MAX_NUMBER_OF_PARTS = 4;
    public static final String SERVANT_WORK = "Day %d servant %s  get %d robot parts\n";
    public static final String SCIENTIST_WORK = "Day %d %s  created %d robots\n";

    //common
    public static final int NUMBER_OF_NIGHTS = 100;
    public static final int DAY_LENGTH = 100;


}
