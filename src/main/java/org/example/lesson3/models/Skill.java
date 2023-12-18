package org.example.lesson3.models;

public class Skill {
    private String name;
    private int timeToStudy;

    public Skill(String name, int timeToStudy) {
        this.name = name;
        this.timeToStudy = timeToStudy;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTimeToStudy() {
        return timeToStudy;
    }

    public void setTimeToStudy(int timeToStudy) {
        this.timeToStudy = timeToStudy;
    }
}
