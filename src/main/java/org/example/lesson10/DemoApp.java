package org.example.lesson10;

import org.example.lesson10.dao.TaskDAO;
import org.example.lesson10.dao.impl.TaskDAOImpl;
import org.example.lesson10.dto.Task;
import org.example.lesson10.utils.Builder;
import org.example.lesson10.utils.HibernateUtil;
import org.example.lesson10.utils.TaskTypes;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.example.lesson10.utils.Constants.MAX_NUMBER_OF_TASKS;
import static org.example.lesson10.utils.Constants.UPDATE;

public class DemoApp {

    public static void main(String[] args) {
        TaskDAO taskDao = new TaskDAOImpl();
        List<Task> tasks = getRandomTasks();
        tasks.forEach(taskDao::save);
        tasks.stream()
                .peek(t -> t.setDescription(t.getDescription() + UPDATE))
                .forEach(taskDao::update);
        tasks.stream()
                .peek(t -> taskDao.get(t.getId()))
                .forEach(System.out::println);

        tasks.forEach(t -> taskDao.delete(t.getId()));

        taskDao.closeSession();
        HibernateUtil.close();
        HibernateUtil.getEntityManager();
    }

    private static List<Task> getRandomTasks() {
        return IntStream.range(0, MAX_NUMBER_OF_TASKS)
                .mapToObj(i -> Builder.buildTask(TaskTypes.getRandomType()))
                .collect(Collectors.toList());
    }
}
