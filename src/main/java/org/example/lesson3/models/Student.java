package org.example.lesson3.models;

import org.example.lesson3.strategies.LearningStrategy;

public class Student {
    private String name;
    private double talentValue = Math.round((Math.random() + 0.1) * 10) * 0.1;
    private LearningStrategy strategy;

    public Student(String name, LearningStrategy strategy) {
        this.name = name;
        this.strategy = strategy;
    }

    public void study(Skill skill){
        strategy.teachStudent(this, skill);
    }

    public void setStrategy(LearningStrategy strategy) {
        this.strategy = strategy;
    }

    public String getName() {
        return name;
    }

    public double getTalentValue() {
        return talentValue;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTalentValue(double talentValue) {
        this.talentValue = talentValue;
    }

    public LearningStrategy getStrategy() {
        return strategy;
    }
}
