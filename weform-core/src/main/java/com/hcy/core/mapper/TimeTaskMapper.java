package com.hcy.core.mapper;

import com.hcy.core.model.TimeTask;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;


@Mapper
@Component(value = "timeTaskMapper")
public interface TimeTaskMapper {

    @Insert("insert into timetask(taskKey,sendType,taskType,formId,userId,finishTime)values(#{task.taskKey},#{task.sendType},#{task.taskType},#{task.formId},#{task.userId},#{task.finishTime});")
    int insert(@Param("task") TimeTask task);


    @Select("select * from timetask where taskKey = #{taskKey} limit 1;")
    TimeTask getTaskByTaskKey(@Param("taskKey") String taskKey);

}