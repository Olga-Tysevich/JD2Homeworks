package org.example.lesson3.strategies;

import org.example.lesson3.models.Skill;
import org.example.lesson3.models.Student;

public interface LearningStrategy {
    void teachStudent(Student student, Skill skill);
}
