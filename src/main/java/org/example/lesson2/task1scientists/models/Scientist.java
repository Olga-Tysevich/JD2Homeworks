package org.example.lesson2.task1scientists.models;

import org.example.lesson2.task1scientists.Competition;

import java.util.*;

import static org.example.lesson2.task1scientists.Constants.*;

public class Scientist extends Thread {
    private List<RobotParts> robotParts = new ArrayList<>();
    private Robot currentRobot = new Robot();
    private final List<Robot> robots = new ArrayList<>();
    private final Servant servant;

    public Scientist(String name, Competition competition) {
        super(name);
        this.servant = new Servant(competition);
    }

    @Override
    public void run() {
        for (int i = 1; i <= NUMBER_OF_NIGHTS; i++) {
            List<RobotParts> todayRobotParts = new ArrayList<>(servant.pickUpRobotParts());
            robotParts.addAll(todayRobotParts);
            System.out.printf(SERVANT_WORK, i, super.getName(), todayRobotParts.size());
            createRobots(i);
            waitNextNight();
        }
    }

    public void createRobots(int day) {
        int robotsCounter = 0;
        boolean isRobotAssembled;
        do {
            isRobotAssembled = createRobot();
            robotsCounter = isRobotAssembled ? ++robotsCounter : robotsCounter;
        } while (isRobotAssembled);
        System.out.printf(SCIENTIST_WORK, day, super.getName(), robotsCounter);
    }

    private boolean createRobot() {
        List<RobotParts> parts = new ArrayList<>(robotParts);
        for (RobotParts part : parts) {
            if (currentRobot.addPart(part)) {
                robotParts.remove(part);
            }
            if (currentRobot.isRobotAssembled()) {
                robots.add(currentRobot);
                currentRobot.resetRobot();
                return true;
            }
        }
        return false;
    }

    private void waitNextNight() {
        try {
            Thread.sleep(DAY_LENGTH);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int getNumberOfRobots() {
        return robots.size();
    }
}
