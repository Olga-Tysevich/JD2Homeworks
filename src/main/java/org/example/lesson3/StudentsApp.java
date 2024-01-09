package org.example.lesson3;

import org.example.lesson3.models.Skill;
import org.example.lesson3.models.Student;
import org.example.lesson3.strategies.LearningStrategy;
import org.example.lesson3.strategies.Type1;
import org.example.lesson3.strategies.Type2;
import org.example.lesson3.strategies.Type3;
import java.util.ArrayList;
import java.util.List;

import static org.example.lesson3.Constants.*;

public class StudentsApp {
    private static int studentCounter;

    public static void main(String[] args) {
        Skill skill = new Skill(SKILL_NAME, DURATION_OF_SKILL_LEARNING);
        List<Student> students = new ArrayList<>();
        students.addAll(generateStudents(NUMBER_OF_STUDENTS_TYPE_1, new Type1()));
        students.addAll(generateStudents(NUMBER_OF_STUDENTS_TYPE_2, new Type2()));
        students.addAll(generateStudents(NUMBER_OF_STUDENTS_TYPE_3, new Type3()));
        students.forEach(student -> System.out.println(student.study(skill)));
    }

    public static List<Student> generateStudents(int numberOfStudents, LearningStrategy strategyType) {
        List<Student> students = new ArrayList<>();
        for (int i = 0; i < numberOfStudents ; i++) {
            students.add(new Student(STUDENT_NUMBER + ++studentCounter, strategyType));
        }
        return students;
    }
}
