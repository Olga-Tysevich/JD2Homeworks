package org.example.lesson10.dao.impl;

import org.example.lesson10.dao.TaskDAO;
import org.example.lesson10.dto.Task;

public class TaskDAOImpl extends DAOImpl<Task> implements TaskDAO {
    @Override
    protected Class<Task> getClazz() {
        return Task.class;
    }
}
