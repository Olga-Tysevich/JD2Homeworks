package org.example.lesson10b3.utils;

import java.time.LocalDate;

import static org.example.lesson10b3.utils.Constants.RANDOM;

public enum TaskTypes {
    TASK(Constants.TASK_NAME, Constants.TASK_DESCRIPTION),
    HOME_TASK(Constants.HOME_TASK_NAME, Constants.HOME_TASK_DESCRIPTION, LocalDate.now(), LocalDate.now().plusDays(1)),
    WORK_TASK(Constants.WORK_TASK_NAME, Constants.WORK_TASK_DESCRIPTION, Constants.WORK_TASK_COST);

    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private Double cost;

    TaskTypes(String name, String description) {
        this.name = name;
        this.description = description;
    }

    TaskTypes(String name, String description, LocalDate startDate, LocalDate endDate) {
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    TaskTypes(String name, String description, Double cost) {
        this.name = name;
        this.description = description;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public Double getCost() {
        return cost;
    }

    public static TaskTypes getRandomType() {
        return TaskTypes.values()[RANDOM.nextInt(TaskTypes.values().length)];
    }
}