package org.example.lesson3;

import org.example.lesson3.models.Skill;
import org.example.lesson3.models.Student;
import org.example.lesson3.strategies.Type1;
import org.example.lesson3.strategies.Type2;
import org.example.lesson3.strategies.Type3;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StudentsAppTest {
    @ParameterizedTest
    @MethodSource("testStudentType1Cases")
    public void testStudentType1(double timeToStudy, String studentName, double talentValue, double timeForOneAction) {
        Skill skill = new Skill("java core", 22);
        Student student = new Student(studentName, new Type1());
        student.setTalentValue(talentValue);
        String expected = studentName + ", Тип 1, талант: " + String.format("%.1f", talentValue)
                + ", общее время на обучение: " + String.format("%.1f", timeToStudy) + ": "
                 + "\n время для разбора: " + String.format("%.1f", timeForOneAction)
                 + "\n время для практики: " + String.format("%.1f", timeForOneAction)
                 + "\n время для нахождения в потоке: " + String.format("%.1f", timeForOneAction);
        String actual = student.study(skill);
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("testStudentType2Cases")
    public void testStudentType2(double timeToStudy, String studentName, double talentValue, double timeForOneAction) {
        Skill skill = new Skill("java core", 22);
        Student student = new Student(studentName, new Type2());
        student.setTalentValue(talentValue);
        String expected = studentName + ", Тип 2, талант: " + String.format("%.1f", talentValue)
                + ", общее время на обучение: " + String.format("%.1f", timeToStudy) + ": "
                + "\n время для разбора: " + String.format("%.1f", timeForOneAction)
                + "\n время для практики: " + String.format("%.1f", timeForOneAction);
        String actual = student.study(skill);
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("testStudentType3Cases")
    public void testStudentType3(double timeToStudy, String studentName, double talentValue, double timeForOneAction) {
        Skill skill = new Skill("java core", 22);
        Student student = new Student(studentName, new Type3());
        student.setTalentValue(talentValue);
        String expected = studentName + ", Тип 3, талант: " + String.format("%.1f", talentValue)
                + ", общее время на обучение: " + String.format("%.1f", timeToStudy) + ": "
                + "\n время для практики: " + String.format("%.1f", timeForOneAction);
        String actual = student.study(skill);
        assertEquals(expected, actual);
    }

    static Stream<Arguments> testStudentType1Cases() {
        return Stream.of(
                Arguments.of(22.0, "Student", 1.0, 7.3),
                Arguments.of(220.0, "Student", 0.1, 73.3),
                Arguments.of(44.0, "Student", 0.5, 14.7),
                Arguments.of(27.5, "Student", 0.8, 9.2)
        );
    }

    static Stream<Arguments> testStudentType2Cases() {
        return Stream.of(
                Arguments.of(44.0, "Student", 1.0, 22),
                Arguments.of(440.0, "Student", 0.1, 220),
                Arguments.of(88.0, "Student", 0.5, 44),
                Arguments.of(55, "Student", 0.8, 27.5)
        );
    }

    static Stream<Arguments> testStudentType3Cases() {
        return Stream.of(
                Arguments.of(66.0, "Student", 1.0, 66),
                Arguments.of(660.0, "Student", 0.1, 660),
                Arguments.of(132.0, "Student", 0.5, 132),
                Arguments.of(82.5, "Student", 0.8, 82.5)
        );
    }

}
