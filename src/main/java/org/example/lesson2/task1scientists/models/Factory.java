package org.example.lesson2.task1scientists.models;

import org.example.lesson2.task1scientists.Competition;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.example.lesson2.task1scientists.Constants.*;

public class Factory extends Thread{
    private Competition competition;
    public Factory(Competition competition) {
        this.competition = competition;
    }

    @Override
    public void run() {
        for (int i = 1; i <= NUMBER_OF_NIGHTS; i++) {
            List<RobotParts> parts = getRandomParts(new Random().nextInt(NUMBER_OF_RANDOM_PARTS) + 1);
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
            parts.add(RobotParts.values()[new Random().nextInt(RobotParts.values().length)]);
        }
        return parts;
    }

}
