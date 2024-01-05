package org.example.lesson3.strategies;

import org.example.lesson3.models.Skill;
import org.example.lesson3.models.Student;

public class Type3 implements LearningStrategy {

    @Override
    public String teachStudent(Student student, Skill skill) {
        int durationOfStudyIndex = 3;
        int numberOfActions = 1;

        double requiredTime = Math.round(skill.getTimeToStudy() * durationOfStudyIndex / student.getTalentValue() * 10) * 0.1;
        double timeToStudy = Math.round((requiredTime / numberOfActions) * 10) * 0.1;

        return student.getName() + ", Тип 3, талант: " + String.format("%.1f", student.getTalentValue())
                + ", общее время на обучение: " + String.format("%.1f", requiredTime) + ": "
                + "\n время для практики: " + String.format("%.1f", timeToStudy);
    }
}