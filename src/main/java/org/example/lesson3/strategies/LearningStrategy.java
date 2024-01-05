package org.example.lesson3.strategies;

import org.example.lesson3.models.Skill;
import org.example.lesson3.models.Student;

public interface LearningStrategy {
    String teachStudent(Student student, Skill skill);
}
