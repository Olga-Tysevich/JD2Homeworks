package org.example.lesson2.task1scientists.models;

import org.example.lesson2.task1scientists.Competition;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.example.lesson2.task1scientists.Constants.NUMBER_OF_RANDOM_PARTS;

public class Servant implements Runnable {
    private Competition competition;
    private List<RobotParts> robotParts = new ArrayList<>();

    public Servant(Competition competition) {
        this.competition = competition;
    }

    @Override
    public void run() {
        int maxNumberOfParts = new Random().nextInt(NUMBER_OF_RANDOM_PARTS) + 1;
        for (int i = 0; i < maxNumberOfParts; i++) {
            RobotParts randomPart = competition.getPart();
            if (randomPart != null) {
                robotParts.add(randomPart);
            }
        }
    }

    public List<RobotParts> getRobotParts() {
        return robotParts;
    }
}