package org.example.lesson3;

import org.example.lesson3.models.Skill;
import org.example.lesson3.models.Student;
import org.example.lesson3.strategies.LearningStrategy;
import org.example.lesson3.strategies.Type1;
import org.example.lesson3.strategies.Type2;
import org.example.lesson3.strategies.Type3;
import java.util.ArrayList;
import java.util.List;

public class StudentsDemo {
    private static int studentCounter;

    public static void main(String[] args) {
        Skill skill = new Skill("java core", 66);
        List<Student> students = new ArrayList<>();
        students.addAll(generateStudents(3, new Type1(3, 1)));
        students.addAll(generateStudents(3, new Type2(2, 2)));
        students.addAll(generateStudents(3, new Type3(1, 3)));
        students.forEach(student -> student.study(skill));
    }

    public static List<Student> generateStudents(int numberOfStudents, LearningStrategy strategyType) {
        List<Student> students = new ArrayList<>();
        for (int i = 0; i < numberOfStudents ; i++) {
            students.add(new Student("Student" + ++studentCounter, strategyType));
        }
        return students;
    }
}
