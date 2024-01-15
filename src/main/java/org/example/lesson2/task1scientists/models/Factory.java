package org.example.lesson2.task1scientists.models;

import org.example.lesson2.task1scientists.Competition;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.example.lesson2.task1scientists.Constants.*;

public class Factory extends Thread {
    private final Competition competition;

    public Factory(Competition competition) {
        this.competition = competition;
    }

    @Override
    public void run() {
        for (int i = 1; i <= NUMBER_OF_NIGHTS; i++) {
            List<RobotParts> parts = createRandomParts(
                    RANDOM.nextInt(FACTORY_MAX_NUMBER_OF_PARTS + 1
                            - FACTORY_MIN_NUMBER_OF_PARTS)
                            + FACTORY_MIN_NUMBER_OF_PARTS);
            competition.putParts(parts);
            System.out.printf(FACTORY_WORK, i, parts.size());
            waitNextNight();
        }
    }

    public List<RobotParts> createRandomParts(int numberOfParts) {
        List<RobotParts> parts = new ArrayList<>();
        for (int i = 0; i < numberOfParts; i++) {
            parts.add(RobotParts.values()[RANDOM.nextInt(RobotParts.values().length)]);
        }
        return parts;
    }

    private void waitNextNight() {
        try {
            Thread.sleep(DAY_LENGTH);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
