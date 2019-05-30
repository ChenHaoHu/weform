package com.hcy.core.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hcy.core.mapper.FormMapper;
import com.hcy.core.mapper.JoinMapper;
import com.hcy.core.mapper.UserMapper;
import com.hcy.core.model.JoinDO;
import com.hcy.core.model.UserDO;
import com.hcy.core.service.ExcelUtil;
import com.hcy.core.service.JoinService;
import com.hcy.core.service.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: 简单DI年华
 * @Date: 18-10-3 23:12
 * @Description: 报名操作
 *
 */
@Service
public class JoinServiceImpl implements JoinService {

    @Autowired
    JoinMapper joinMapper;

    @Autowired
    FormMapper formMapper;

    @Autowired
    TimeUtil timeUtil;

    @Autowired
    ExcelUtil excelUtil;

    @Autowired
    UserMapper userMapper;


    @Override
    public Integer joinForm(String formid, String userid, String content) {
        JSONObject jsonObject = JSON.parseObject(content);
        JoinDO join = new JoinDO(formid,userid,jsonObject.toJSONString(),timeUtil.getNowTime(),false);
        joinMapper.addJoinData(join);
        return join.getId();
    }

    @Override
    public List<JoinDO> getJoinData(String formid, String userid) {
        return null;
    }

    @Override
    public String deleteJoinData(String id) {

        Integer formid = joinMapper.getFormidById(id);
        String back = formMapper.getPasswordByFormid(formid);
        joinMapper.deleteDataById(id);

        return back;
    }

    @Override
    public String getJoinPassword(String id) {

        Integer formid = joinMapper.getFormidById(id);
        String back = formMapper.getPasswordByFormid(formid);
        return back;
    }

    @Override
    public List getJoinlistByUserid(Integer userid) {
        List list = new ArrayList();
        List<JoinDO> joinlistByUserid = joinMapper.getJoinlistByUserid(userid);
        for (int i = 0; i < joinlistByUserid.size(); i++) {
            Map map = new HashMap();
            map.put("sign",joinlistByUserid.get(i));
            map.put("form",formMapper.getFormByFormid(joinlistByUserid.get(i).getFormid()).get(0));
            list.add(map);
        }
        return list;
    }

    @Override
    public String exportJoinData(Integer formid) {

        String back =  excelUtil.export(String.valueOf(formid));

        return back;
    }

    @Override
    public List getUserDataByFormid(Integer formid) {

        List<Map> back = new ArrayList();

        List<String> userlist = joinMapper.getUseridsById(formid);

        System.out.println(userlist.size());
        for (int i = 0; i < userlist.size(); i++) {
            UserDO user = userMapper.findUserByUserid(userlist.get(i));
            System.out.println(user);
            Map map = new HashMap();
            map.put("name",user.getName());
            map.put("iconurl",user.getAvatar());
            back.add(map);
        }

        return back;
    }
}
