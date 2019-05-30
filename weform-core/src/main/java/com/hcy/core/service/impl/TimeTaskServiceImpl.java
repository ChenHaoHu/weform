package com.hcy.core.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.hcy.Identifier;
import com.hcy.core.mapper.TimeTaskMapper;
import com.hcy.core.model.TimeTask;
import com.hcy.core.service.TimeTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName: TimeTaskServiceImpl
 * @Author: hcy
 * @Description:
 * @Date: 2019-05-18 19:30
 * @Version: 1.0
 **/

@Service
public class TimeTaskServiceImpl implements TimeTaskService {

    @Autowired
    TimeTaskMapper timeTaskMapper;

    @Reference(check = false)
    Identifier identifier;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private String TIME_TASK_REDIS_ZSET = "TimeTask";


    @Override
    @Transactional
    public boolean addTask(TimeTask task) {
      try{
          if(identifier == null){
            throw new Exception();
          }
          String taskKey  = identifier.getIdentifier(10,10);
          //add to databse
          task.setTaskKey(taskKey);
          timeTaskMapper.insert(task);
          //add to redis
          redisTemplate.opsForZSet().add(TIME_TASK_REDIS_ZSET,taskKey, task.getFinishTime());
          return true;
      }catch (Exception e){
          e.fillInStackTrace();
          return false;
      }
    }

    @Override
    public TimeTask getTask(String taskKey) {

        TimeTask taskByTaskKey = timeTaskMapper.getTaskByTaskKey(taskKey);

        return taskByTaskKey;
    }

    @Override
    public boolean updateTask(String taskKey, TimeTask task) {
        return false;
    }

    @Override
    public boolean execteTask(String taskKey) {
        return false;
    }
}
