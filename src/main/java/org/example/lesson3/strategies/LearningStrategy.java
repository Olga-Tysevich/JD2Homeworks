package org.example.lesson3.strategies;

import org.example.lesson3.models.Skill;
import org.example.lesson3.models.Student;

public abstract class LearningStrategy {
    protected int numberOfActions;
    protected int learningRate;

    public LearningStrategy(int numberOfActions, int learningRate) {
        this.numberOfActions = numberOfActions;
        this.learningRate = learningRate;
    }

    public abstract void teachStudent(Student student, Skill skill);
}
