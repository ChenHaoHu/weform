package com.hcy.core.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hcy.core.service.WxApi;
import com.squareup.okhttp.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: 简单DI年华
 * @Date: 18-8-23 16:02
 * @Description: 微信官方api处理
 */

@Service

public class WxApiImpl implements WxApi {

    private static final String  ACCESS_TOKEN = "access_token";
    private static final MediaType TYPE_JSON = MediaType.parse("application/json; charset=utf-8");

    @Value("${wxapp.appid}")
    String appid;//填写appid
    @Value("${wxapp.appsecret}")
    String appsecret;//填写对应appsecret

    @Autowired
    StringRedisTemplate redisTemplate;

    @Override
    public String getOpenid(String code) {

        String reslut = "none";
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url("https://api.weixin.qq.com/sns/jscode2session?appid="+ appid +"&secret="+appsecret+"&js_code="+code+"&grant_type=authorization_code")
                    .build();
            Response response = client.newCall(request).execute();
            String str = response.body().string();
            System.out.println(str);
            JSONObject json= JSON.parseObject(str);
            System.out.println(json);
            reslut = json.getString("openid");
            System.out.println(reslut);
        }catch (Exception e){
            System.out.println(e.getStackTrace());
        }
        return reslut;
    }

    /**
     * 获取 access_token  定时 7200 s
     * @return
     */
    @Override
    public String getAccessToken() {

        String reslut = "none";
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+ appid +"&secret="+appsecret)
                    .build();
            Response response = client.newCall(request).execute();
            String str = response.body().string();
            JSONObject json= JSON.parseObject(str);
            reslut = json.getString(ACCESS_TOKEN);
        }catch (Exception e){
            System.out.println(e.getStackTrace());
        }
        return reslut;
    }

    @Override
    public String getRealToken() {
        String access_token = redisTemplate.opsForValue().get(ACCESS_TOKEN);
        return access_token;
    }



    @Override
   // @Scheduled(fixedRate = 7000*1000) //官方是 7200 s 更新 这里写成 7000 s 更新
    public String updateToken() {
        Logger logger = LoggerFactory.getLogger(WxApiImpl.class);
        String access_token = getAccessToken();
        redisTemplate.opsForValue().set(ACCESS_TOKEN,access_token);
        logger.debug("更新 access_token ："+access_token );
        System.out.println("更新 access_token ："+access_token);
        return null;
    }


    /**
     * 这个方法发送模板消息， 根据 template_id 处理不同的模板
     *
     * 【NOTICE】 不同模板的 list 大小不同 请根据实际需要处理
     *
     * @param list
     * @param touser
     * @param template_id
     * @param page
     * @param form_id
     * @return
     */
    @Override //https://developers.weixin.qq.com/miniprogram/dev/api/sendTemplateMessage.html
    public boolean sendTemplateMessage(List<String> list, String touser, String template_id, String page, String form_id)  {

        JSONObject jsonObject = new JSONObject();
        String token = getRealToken();

        for (int i = 0; i < list.size(); i++) {
            JSONObject item = new JSONObject();
            item.put("value",list.get(i));
            jsonObject.put("keyword"+(i+1),item);
        }

        //拼接data
        JSONObject data = new JSONObject();
        data.put("touser",touser);
        data.put("access_token",token);
        data.put("template_id",template_id);
        data.put("page",page);
        data.put("form_id",form_id);
        data.put("data",jsonObject);

        System.out.println(data);

        try {
            OkHttpClient client = new OkHttpClient();
            RequestBody postBody = RequestBody.create(TYPE_JSON,data.toJSONString());
            Request request = new Request.Builder()
                    .url("https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send?access_token="+token)
                    .post(postBody)
                    .build();
            Response response = client.newCall(request).execute();
            System.out.println(response.body().string());
        }catch (Exception e){
            System.out.println(e.getStackTrace());
        }

        return false;
    }


}
