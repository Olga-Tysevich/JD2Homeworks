package org.example.lesson2;

import org.example.lesson2.models.Factory;
import org.example.lesson2.models.RobotParts;
import org.example.lesson2.models.Scientist;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import static org.example.lesson2.Constants.*;
/*Двое безумных учёных устроили соревнование, кто из них соберёт больше роботов за 100 ночей.
Для этого каждую ночь каждый из них отправляет своего прислужника на свалку фабрики роботов за деталями.
Чтобы собрать одного робота им нужно:
Голова, Тело, Левая рука, Правая рука, Левая нога, Правая нога, CPU, RAM, HDD
В первую ночь на свалке находится 20 случайных деталей. Каждую ночь фабрика выбрасывает на свалку от 1 до 4 случайных деталей.
В то же самое время прислужники обоих учёных отправляются на свалку, и каждый собирает от 1 до 4 случайных деталей.
Если на свалке деталей нет – прислужник уходит ни с чем.
Затем они возвращаются домой и отдают детали хозяевам.
Учёные пытаются собрать как можно больше роботов из деталей, которые они получили.
Написать программу, симулирующую этот процесс. Для симуляции принять, что каждая ночь наступает через 100мс.

Фабрика и два прислужника действуют в одно и то же время.
После 100 ночей (около 10 секунд) определить победителя соревнования.
*/

public class Competition {
    private List<RobotParts> dump;
    private List<Scientist> scientist = new ArrayList<>();
    private Factory factory;

    public Competition() {
        this.factory = new Factory(this);
        for (int i = 1; i <= NUMBER_OF_SCIENTIST; i++) {
            scientist.add(new Scientist(i, this));
        }
        dump = factory.getRandomPart(INITIAL_NUMBER_OF_PARTS);
    }
    public synchronized void putParts(List<RobotParts> parts) {
        dump.addAll(parts);
    }

    public synchronized RobotParts getPart() {
        int randomPartIndex = dump.size() != 0 ? new Random().nextInt(dump.size()) : 0;
        return dump.size() != 0 ? dump.remove(randomPartIndex) : null;
    }

    public void startCompetition() {
        for (int i = 0; i < NUMBER_OF_NIGHTS; i++) {
            new Thread(factory).start();
            scientist.forEach(sc -> new Thread(sc.getServant()).start());
            try {
                Thread.sleep(DAY_LENGTH);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        printResult();
    }

    private void printResult() {
        scientist.forEach(s -> System.out.println(s.getName() + ", robotParts: " + s.getServant().getRobotParts().size()));
        Scientist winner = scientist.stream().max(Comparator.comparingInt(Scientist::getNumberOfRobots)).orElse(null);
        scientist.forEach(sc -> System.out.println("Number of robots a " + sc.getName() + " has: " + sc.getNumberOfRobots()));
        assert winner != null;
        System.out.println("Winner: " + winner.getName());
    }
}
