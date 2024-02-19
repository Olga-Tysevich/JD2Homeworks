package org.example.lesson10b2.dao.impl.utils;

import org.example.lesson10b2.dto.Address;
import org.example.lesson10b2.dto.HomeTask;
import org.example.lesson10b2.dto.Task;
import org.example.lesson10b2.dto.WorkTask;
import org.example.lesson10b2.utils.TaskTypes;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.example.lesson10b2.dao.impl.utils.MockConstants.*;

public class MockUtils {

    public static List<Task> getRandomTasks() {
        return IntStream.range(0, MAX_NUMBER_OF_TASKS)
                .mapToObj(i -> getTask(TaskTypes.getRandomType()))
                .collect(Collectors.toList());
    }

    public static Task getTask(TaskTypes type) {
        if (TaskTypes.TASK == type) {
            return buildOrdinaryTask(type);
        } else if (TaskTypes.HOME_TASK == type) {
            return buildHomeTask(type);
        } else if ((TaskTypes.WORK_TASK == type)) {
            return buildWorkTask(type);
        } else {
            return null;
        }
    }

    public static Address buildAddress() {
        return Address.builder()
                .city(CITY + RANDOM.nextInt(MAX_RANDOM_NUMBER) + 1)
                .street(STREET + RANDOM.nextInt(MAX_RANDOM_NUMBER) + 1)
                .build();
    }

    private static Task buildOrdinaryTask(TaskTypes type) {
        int taskNumber = RANDOM.nextInt(MAX_RANDOM_NUMBER) + 1;
        return Task.builder()
                .name(type.getName() + taskNumber)
                .description(type.getDescription() + taskNumber)
                .build();
    }

    private static Task buildHomeTask(TaskTypes type) {
        int taskNumber = RANDOM.nextInt(MAX_RANDOM_NUMBER) + 1;
        return HomeTask.builder()
                .name(type.getName() + taskNumber)
                .description(type.getDescription() + taskNumber)
                .startDate(type.getStartDate().plusDays(taskNumber))
                .endDate(type.getEndDate().plusDays(taskNumber))
                .address(buildAddress())
                .build();
    }

    private static Task buildWorkTask(TaskTypes type) {
        int taskNumber = RANDOM.nextInt(MAX_RANDOM_NUMBER) + 1;
        return WorkTask.builder()
                .name(type.getName() + taskNumber)
                .description(type.getDescription() + taskNumber)
                .cost(type.getCost() + taskNumber)
                .build();
    }
}