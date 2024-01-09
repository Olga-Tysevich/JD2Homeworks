package org.example.lesson3.strategies;

import org.example.lesson3.models.Skill;
import org.example.lesson3.models.Student;

import static org.example.lesson3.Constants.DURATION_OF_STUDY_INDEX_TYPE_2;

public class Type2 implements LearningStrategy {
    @Override
    public String teachStudent(Student student, Skill skill) {
        int numberOfActions = 2;

        double durationOfStudy = Math.round(skill.getTimeToStudy() * DURATION_OF_STUDY_INDEX_TYPE_2 / student.getTalentValue() * 10) * 0.1;
        double durationOfStudyOneSection = Math.round((durationOfStudy / numberOfActions) * 10) * 0.1;

        return student.getName() + ", Тип 2, талант: " + String.format("%.1f", student.getTalentValue())
                + ", общее время на обучение: " + String.format("%.1f", durationOfStudy) + ": "
                + "\n время для разбора: " + String.format("%.1f", durationOfStudyOneSection)
                + "\n время для практики: " + String.format("%.1f", durationOfStudyOneSection);
    }
}