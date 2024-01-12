package org.example.lesson2.task1scientists.models;

import org.example.lesson2.task1scientists.Competition;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.example.lesson2.task1scientists.Constants.*;

public class Servant implements Runnable {
    private Competition competition;
    private List<RobotParts> robotParts = new ArrayList<>();
    private Random random = new Random();

    public Servant(Competition competition) {
        this.competition = competition;
    }

    @Override
    public void run() {
        for (int i = 1; i <= NUMBER_OF_NIGHTS; i++) {
            int maxNumberOfParts = random.nextInt(SERVANT_MAX_NUMBER_OF_PARTS - SERVANT_MIN_NUMBER_OF_PARTS) + SERVANT_MIN_NUMBER_OF_PARTS;
            List<RobotParts> todayRobotParts = new ArrayList<>();
            for (int j = 0; j < maxNumberOfParts; j++) {
                RobotParts randomPart = competition.getPart();
                if (randomPart != null) {
                    todayRobotParts.add(randomPart);
                }
            }
            robotParts.addAll(todayRobotParts);
            System.out.println("Day " + i + " Servant " + Thread.currentThread().getName() + " get " + todayRobotParts.size() + " robot parts");
            waitNextNight();
        }
    }

    private void waitNextNight() {
        try {
            Thread.sleep(DAY_LENGTH);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public List<RobotParts> getRobotParts() {
        return robotParts;
    }
}