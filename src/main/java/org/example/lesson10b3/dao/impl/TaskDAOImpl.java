package org.example.lesson10b3.dao.impl;

import org.example.lesson10b3.dao.TaskDAO;
import org.example.lesson10b3.dto.Task;

public class TaskDAOImpl extends DAOImpl<Task, Integer> implements TaskDAO {
    @Override
    protected Class<Task> getClazz() {
        return Task.class;
    }
}
