package org.example.lesson2.task1scientists.models;

import org.example.lesson2.task1scientists.Competition;

import java.util.ArrayList;
import java.util.List;

import static org.example.lesson2.task1scientists.Constants.*;

public class Servant {
    private Competition competition;

    public Servant(Competition competition) {
        this.competition = competition;
    }

    public List<RobotParts> pickUpRobotParts() {
        int maxNumberOfParts = RANDOM.nextInt(SERVANT_MAX_NUMBER_OF_PARTS + 1
                - SERVANT_MIN_NUMBER_OF_PARTS)
                + SERVANT_MIN_NUMBER_OF_PARTS;
        List<RobotParts> todayRobotParts = new ArrayList<>();
        for (int j = 0; j < maxNumberOfParts; j++) {
            RobotParts randomPart = competition.getPart();
            if (randomPart != null) {
                todayRobotParts.add(randomPart);
            }
        }
        return todayRobotParts;
    }
}