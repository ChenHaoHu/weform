package com.hcy.core.service;


import com.hcy.core.model.TimeTask;
import org.springframework.stereotype.Service;


@Service
public interface TimeTaskService {
    boolean addTask(TimeTask task);
    TimeTask getTask(String taskKey);
    boolean updateTask(String taskKey,TimeTask task);
    boolean execteTask(String taskKey);
}
