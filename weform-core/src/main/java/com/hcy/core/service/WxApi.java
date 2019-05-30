package com.hcy.core.service;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: 简单DI年华
 * @Date: 18-8-23 16:00
 * @Description:
 */
@Service
public interface WxApi {
    String getOpenid(String code);
    String getAccessToken(); //单纯获取
    String getRealToken();  // 在 redis上获取有效token
    String updateToken(); //定时器刷新token
    boolean sendTemplateMessage(List<String> list, String touser, String template_id, String page, String form_id); //发送创建成功消息
}
