package org.example.lesson2.task1scientists.models;

import org.example.lesson2.task1scientists.Competition;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.example.lesson2.task1scientists.Constants.*;

public class Factory extends Thread {
    private Competition competition;
    private Random random = new Random();

    public Factory(Competition competition) {
        this.competition = competition;
    }

    @Override
    public void run() {
        for (int i = 1; i <= NUMBER_OF_NIGHTS; i++) {
            List<RobotParts> parts = getRandomParts(
                    random.nextInt(FACTORY_MAX_NUMBER_OF_PARTS - FACTORY_MIN_NUMBER_OF_PARTS) + FACTORY_MIN_NUMBER_OF_PARTS);
            competition.putParts(parts);
            System.out.println("Day " + i + ", factory put " + parts.size() + " robot parts");
            try {
                Thread.sleep(DAY_LENGTH);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public List<RobotParts> getRandomParts(int numberOfParts) {
        List<RobotParts> parts = new ArrayList<>();
        for (int i = 0; i < numberOfParts; i++) {
            parts.add(RobotParts.values()[random.nextInt(RobotParts.values().length)]);
        }
        return parts;
    }

}
