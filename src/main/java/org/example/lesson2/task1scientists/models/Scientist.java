package org.example.lesson2.task1scientists.models;

import org.example.lesson2.task1scientists.Competition;

import java.util.*;

public class Scientist {
    private String name;
    private List<Robot> robots;
    private Servant servant;

    public Scientist(String number, Competition competition) {
        this.name =  number;
        this.robots = new ArrayList<>();
        this.servant = new Servant(competition);
    }
    public int getNumberOfRobots() {
        boolean isRobotAssembled;
        do {
            isRobotAssembled = createRobot();
        } while (isRobotAssembled);
        return robots.size();
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

    public String getName() {
        return this.name;
    }

    public Servant getServant() {
        return servant;
    }
}
