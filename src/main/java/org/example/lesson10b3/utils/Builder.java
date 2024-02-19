package org.example.lesson10b3.utils;

import org.example.lesson10b3.dto.Address;
import org.example.lesson10b3.dto.HomeTask;
import org.example.lesson10b3.dto.Task;
import org.example.lesson10b3.dto.WorkTask;

import static org.example.lesson10b3.utils.Constants.*;

public final class Builder {

    private Builder() {
    }

    public static Task buildTask(TaskTypes type) {
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