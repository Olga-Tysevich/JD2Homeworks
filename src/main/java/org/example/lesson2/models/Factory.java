package org.example.lesson2.models;

import org.example.lesson2.Competition;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.example.lesson2.Constants.NUMBER_OF_RANDOM_PARTS;

public class Factory extends Thread{
    private Competition competition;
    private int dayCounter;

    public Factory(Competition competition) {
        this.competition = competition;
    }

    @Override
    public void run() {
        List<RobotParts> parts = new ArrayList<>();
        for (int i = 0; i < new Random().nextInt(NUMBER_OF_RANDOM_PARTS) + 1; i++) {
            parts.add(RobotParts.values()[new Random().nextInt(RobotParts.values().length)]);
        }
        competition.putParts(parts);
        dayCounter++;
        System.out.println("Day: " + dayCounter + ", dump: " + parts.size());
    }

    public List<RobotParts> getRandomPart(int numberOfParts) {
        List<RobotParts> parts = new ArrayList<>();
        for (int i = 0; i < numberOfParts; i++) {
            parts.add(RobotParts.values()[new Random().nextInt(RobotParts.values().length)]);
        }
        return parts;
    }

}
