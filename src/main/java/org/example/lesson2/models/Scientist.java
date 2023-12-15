package org.example.lesson2.models;

import org.example.lesson2.Competition;

import java.util.*;

public class Scientist {
    private String name;
    private List<Robot> robots;
    private Servant servant;

    public Scientist(int number, Competition competition) {
        this.name = "Scientist" + number;
        this.robots = new ArrayList<>();
        this.servant = new Servant(competition);
    }
    private boolean createRobot() {
        List<RobotParts> parts = new ArrayList<>(servant.getRobotParts());
        Robot robot = new Robot();
        for (RobotParts part : parts) {
            if (robot.addPart(part)){
                servant.getRobotParts().remove(part);
            }
            if (robot.isRobotAssembled()){
                return robots.add(robot);
            }
        }
        return false;
    }

    public int getNumberOfRobots() {
        boolean isRobotAssembled;
        do {
            isRobotAssembled = createRobot();
        } while (isRobotAssembled);
        return robots.size();
    }

    public String getName() {
        return this.name;
    }

    public Servant getServant() {
        return servant;
    }
}
