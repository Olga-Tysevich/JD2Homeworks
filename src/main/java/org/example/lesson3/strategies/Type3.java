package org.example.lesson3.strategies;

import org.example.lesson3.models.Skill;
import org.example.lesson3.models.Student;

import java.text.DecimalFormat;

public class Type3 implements LearningStrategy {

    @Override
    public void teachStudent(Student student, Skill skill) {
        int durationOfStudyIndex = 3;
        int numberOfActions = 1;

        double requiredTime = Math.round(skill.getTimeToStudy() * durationOfStudyIndex/ student.getTalentValue() * 10) * 0.1;
        double timeToStudy = Math.round((requiredTime / numberOfActions) * 10) * 0.1;

        DecimalFormat format = new DecimalFormat("###.#");
        String message = student.getName() + ", Тип 3, талант: " + format.format(student.getTalentValue())
                + ", общее время на обучение: " + format.format(requiredTime) + ": " + "\n время для практики: " + format.format(timeToStudy);
        System.out.println(message + "\n");
    }
}