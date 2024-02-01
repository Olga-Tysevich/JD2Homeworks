package org.example.lesson2.task1scientists;

import org.example.lesson2.task1scientists.models.Factory;
import org.example.lesson2.task1scientists.models.RobotParts;
import org.example.lesson2.task1scientists.models.Scientist;

import java.util.ArrayList;
import java.util.List;

import static org.example.lesson2.task1scientists.Constants.*;
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
    private final List<RobotParts> dump;
    private final List<Scientist> scientists = new ArrayList<>();
    private final Factory factory;

    public Competition() {
        this.factory = new Factory(this);
        for (int i = 1; i <= NUMBER_OF_SCIENTIST; i++) {
            scientists.add(new Scientist(SCIENTIST_NAME + i, this));
        }
        dump = factory.createRandomParts(INITIAL_NUMBER_OF_PARTS);
    }

    public synchronized void putParts(List<RobotParts> parts) {
        dump.addAll(parts);
    }

    public synchronized RobotParts getPart() {
        return !dump.isEmpty() ? dump.remove(RANDOM.nextInt(dump.size())) : null;
    }

    public void startCompetition() {
        if (scientists.size() > 1) {
            try {
                scientists.forEach(Thread::start);
                factory.start();
                for (Scientist scientist : scientists) {
                    scientist.join();
                }
                factory.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            determineWinner();
        } else {
            System.out.println(ERROR_MESSAGE);
        }
    }

    private void determineWinner() {
        int maxAmountOfRobots = scientists.stream()
                .map(Scientist::getNumberOfRobots)
                .max(Integer::compareTo)
                .orElse(-1);
        List<Scientist> winners = new ArrayList<>();
        scientists.forEach(s -> {
            if (s.getNumberOfRobots() == maxAmountOfRobots) {
                winners.add(s);
            }
        });
        printResult(winners);
    }

    private void printResult(List<Scientist> winners) {
        scientists.forEach(scientist -> System.out.printf(NUMBER_OF_ROBOTS_MESSAGE, scientist.getName(), scientist.getNumberOfRobots()));
        if (HAS_ONE_WINNER.test(winners)) {
            System.out.printf(ONE_WINNER_MESSAGE, winners.get(WINNER).getName());
        } else if (scientists.size() == winners.size()) {
            System.out.println(GAME_DRAW_MESSAGE);
        } else if (HAS_MANY_WINNER.test(winners)) {
            System.out.println(MANY_WINNERS_MESSAGE);
            winners.forEach(w -> System.out.println(w.getName()));
        } else {
            System.out.println(NOBODY_WON_MESSAGE);
        }
    }
}
