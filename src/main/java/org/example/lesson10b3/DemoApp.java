package org.example.lesson10b3;

import org.example.lesson10b3.dao.TaskDAO;
import org.example.lesson10b3.dao.impl.TaskDAOImpl;
import org.example.lesson10b3.dto.Task;
import org.example.lesson10b3.utils.Builder;
import org.example.lesson10b3.utils.HibernateUtil;
import org.example.lesson10b3.utils.TaskTypes;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.example.lesson10b3.utils.Constants.*;

public class DemoApp {

    public static void main(String[] args) {
        TaskDAO taskDao = new TaskDAOImpl();
        List<Task> tasks = getRandomTasks();
        tasks.forEach(taskDao::save);
        tasks.stream()
                .peek(t -> t.setDescription(t.getDescription() + UPDATE))
                .forEach(taskDao::update);
        tasks.stream()
                .map(t -> taskDao.get(t.getId()))
                .forEach(System.out::println);

        tasks.stream()
                .limit(NUMBER_OF_TASKS_TO_DELETE)
                .forEach(t -> taskDao.delete(t.getId()));

        taskDao.closeSession();
        HibernateUtil.close();
    }

    private static List<Task> getRandomTasks() {
        return IntStream.range(0, MAX_NUMBER_OF_TASKS)
                .mapToObj(i -> Builder.buildTask(TaskTypes.getRandomType()))
                .collect(Collectors.toList());
    }
}