package com.hcy.message.model;

import lombok.Data;

import java.util.Date;


@Data
public class TimeTask {
    private Integer id;

    private String taskKey;

    private Integer sendType;

    private Integer taskType;

    private Integer formId;

    private Integer userId;

    private Date createTime;

    private Date updateTime;

    private long finishTime;


    public TimeTask() {
        super();
    }


}