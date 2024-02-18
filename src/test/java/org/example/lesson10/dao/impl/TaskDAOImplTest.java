package org.example.lesson10.dao.impl;

import org.example.lesson10.dao.TaskDAO;
import org.example.lesson10.dto.Task;
import org.example.lesson10.utils.HibernateUtil;
import org.example.lesson10.utils.MockUtils;
import org.example.lesson10.utils.TaskTypes;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.example.lesson10.utils.MockConstants.EXPECTED_DESCRIPTION;
import static org.junit.jupiter.api.Assertions.*;

class TaskDAOImplTest {
    private TaskDAO taskDAO = new TaskDAOImpl();
    private List<Task> tasks = MockUtils.getRandomTasks();

    @AfterAll
    public static void closeHibernate() {
        HibernateUtil.close();
    }

    @Test
    void testSave() {
        tasks.forEach(taskDAO::save);
        List<Integer> idList = tasks.stream()
                .map(Task::getId)
                .collect(Collectors.toList());

        assertIterableEquals(tasks,
                idList.stream()
                        .map(taskDAO::get)
                        .collect(Collectors.toList()));
        taskDAO.closeSession();
    }

    @Test
    void testUpdate() {
        Task task = MockUtils.getTask(TaskTypes.HOME_TASK);
        taskDAO.save(task);
        assert task != null;
        task.setDescription(EXPECTED_DESCRIPTION);
        taskDAO.update(task);
        String actual = taskDAO.get(task.getId()).getDescription();

        assertEquals(EXPECTED_DESCRIPTION, actual);
    }

    @Test
    void testDelete() {
        Task task = MockUtils.getTask(TaskTypes.HOME_TASK);
        taskDAO.save(task);
        assert task != null;
        int id = task.getId();
        taskDAO.delete(id);
        Task actual = taskDAO.get(id);

        assertNull(actual);
    }

}