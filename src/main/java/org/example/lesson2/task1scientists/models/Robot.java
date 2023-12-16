package org.example.lesson2.task1scientists.models;

import java.util.HashSet;
import java.util.Set;

public class Robot {
    private Set<RobotParts> parts = new HashSet<>();

    public boolean addPart(RobotParts part) {
        return parts.add(part);
    }

    public boolean isRobotAssembled() {
        return parts.size() == RobotParts.values().length;
    }
}
