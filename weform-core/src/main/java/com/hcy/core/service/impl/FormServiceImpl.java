package com.hcy.core.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.hcy.core.common.request.RequestForm;
import com.hcy.core.mapper.FormMapper;
import com.hcy.core.mapper.UserMapper;
import com.hcy.core.model.FormDO;
import com.hcy.core.model.UserDO;
import com.hcy.core.service.FormService;
import com.hcy.core.service.MathUtil;
import com.hcy.core.service.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: 简单DI年华
 * @Date: 18-10-1 12:05
 * @Description: 表单操作实体类
 */

@Service
public class FormServiceImpl implements FormService {


    //引入表单操作DAO
    @Autowired
    FormMapper formMapper;
    //注入时间操作
    @Autowired
    TimeUtil timeUtil;
    //注入数学运算
    @Autowired
    MathUtil mathUtil;
    //注入人员信息
    @Autowired
    UserMapper userMapper;


    @Override
    public String addForm(RequestForm data) {

        FormDO form = new FormDO(data.getUserid(),data.getTitle(),data.getType(),
                data.getMode(),data.getStart(),data.getEnd(),timeUtil.getNowTime(),
                data.getMaxnum(),data.getIntro(),data.getIconurl(), data.getPosterurl(),
                data.getIspublic(),data.getUsername(),data.getTags());

        formMapper.insertform(form);
        String password =  mathUtil.getFormPassWord(form.getFormid());

        formMapper.addpassword(form.getFormid(),password);

        JSONObject jsonObject  = new JSONObject();
        jsonObject.put("formid",form.getFormid());
        jsonObject.put("password",password);

        return jsonObject.toJSONString();
    }

    /**
     * 根据密码获取信息
     * @param password
     * @return
     */
    @Override
    public FormDO getFormByPassword(String password) {
        FormDO form  = formMapper.getFromByPassword(password);
        return form;
    }

    @Override
    public String getFormTitle(Integer formid) {
        String titleByFormid = formMapper.getTitleByFormid(formid + "");
        return titleByFormid;
    }

    @Override
    public List getFormByTag(String tag) {
        List<FormDO> formByTag = formMapper.getFormByTag(tag);
        List<Map> back = new ArrayList<>();
        for (int i = 0; i < formByTag.size(); i++) {
            Integer userid = formByTag.get(i).getUserid();
            UserDO userByUserid = userMapper.findUserByUserid(userid + "");
            Map map = new HashMap();
            map.put("username",userByUserid.getName());
            map.put("usericon",userByUserid.getAvatar());
            map.put("title",formByTag.get(i).getTitle());
            map.put("intro",formByTag.get(i).getIntro());
            map.put("icon",formByTag.get(i).getIconurl());
            //这里把表单的密匙当作id传，便于前端处理
            map.put("id",formByTag.get(i).getPassword());
            map.put("time",formByTag.get(i).getCreatetime());
            map.put("type",formByTag.get(i).getType());
            back.add(map);
        }

        return back;
    }

    @Override
    public String deleForm() {
        return null;
    }

    @Override
    public String editForm() {
        return null;
    }

    @Override
    public boolean checkForm(String password) {
        FormDO fromByPassword = formMapper.getFromByPassword(password);

        if(fromByPassword == null){
            return false;
        }

        return true;
    }

    @Override
    public Map getNum() {
        Integer form = formMapper.getFormNum();
        Integer activity = formMapper.getAvtivityNum();
        Map map = new HashMap();
        map.put("form",form);
        map.put("activity",activity);

        return map;
    }

    @Override
    public String getPasswordByFormId(Integer formid) {
        String passwordByFormId = formMapper.getPasswordByFormId(formid);

        return passwordByFormId;
    }

    @Override
    public List getFormByUserid(Integer useid) {
        List<FormDO> formByUserid = formMapper.getFormByUserid(useid);
        return formByUserid;
    }

    @Override
    public List getFormByPage(Integer pageSize, Integer pageNum) {

        PageHelper.startPage(pageNum, pageSize);
        List<FormDO> allForm = formMapper.getAllForm();
        return allForm;
    }

    @Override
    public List getActivityByPage(Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        List<FormDO> allActivity = formMapper.getAllActivity();
        return allActivity;
    }
}
