package com.hcy.message.service.impl;

import com.hcy.core.model.TimeTask;
import com.hcy.core.service.TimeTaskService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TimeTaskServiceImplTest {

    @Autowired
    TimeTaskService timeTaskService;

    @Test
    public void addTask() {
        TimeTask timeTask = new TimeTask();
        timeTask.setFormId(100);
        timeTask.setSendType(1);
        timeTask.setTaskType(1);
        timeTask.setUserId(100);
        timeTask.setFinishTime(System.currentTimeMillis());
        timeTaskService.addTask(timeTask);
    }

    @Test
    public void deleTask() {
    }

    @Test
    public void updateTask() {
    }

    @Test
    public void execteTask() {
    }


    @Test
    public void getTask() {
    }

    @Test
    public void updateTask1() {
    }

    @Test
    public void execteTask1() {
    }
}