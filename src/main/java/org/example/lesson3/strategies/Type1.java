package org.example.lesson3.strategies;

import org.example.lesson3.models.Skill;
import org.example.lesson3.models.Student;

public class Type1 implements LearningStrategy {

    @Override
    public String teachStudent(Student student, Skill skill) {
        int durationOfStudyIndex = 1;
        int numberOfActions = 3;

        double requiredTime = Math.round(skill.getTimeToStudy() * durationOfStudyIndex / student.getTalentValue() * 10) * 0.1;
        double timeToStudy = Math.round((requiredTime / numberOfActions) * 10) * 0.1;

        return student.getName() + ", Тип 1, талант: " + String.format("%.1f", student.getTalentValue())
                + ", общее время на обучение: " + String.format("%.1f", requiredTime) + ": "
                + "\n время для разбора: " + String.format("%.1f", timeToStudy)
                + "\n время для практики: " + String.format("%.1f", timeToStudy)
                + "\n время для нахождения в потоке: " + String.format("%.1f", timeToStudy);
    }
}