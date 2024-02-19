package org.example.lesson10b2.dao.impl;

import org.example.lesson10b2.dao.TaskDAO;
import org.example.lesson10b2.dto.Task;

public class TaskDAOImpl extends DAOImpl<Task> implements TaskDAO {


    @Override
    protected Class<Task> getObjectClass() {
        return Task.class;
    }
}