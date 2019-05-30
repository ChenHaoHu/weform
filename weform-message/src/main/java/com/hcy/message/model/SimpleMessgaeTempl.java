package com.hcy.message.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * @ClassName: SimpleMessgaeTempl
 * @Author: hcy
 * @Description:
 * @Date: 2019-05-25 22:24
 * @Version: 1.0
 **/

@Data
public class SimpleMessgaeTempl implements Serializable {
    String send;
    String topic;
    String templ;
    Map<String,String> content;
}
