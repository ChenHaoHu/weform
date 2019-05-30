package com.hcy.core.service.impl;


import com.hcy.core.mapper.FormIdMapper;
import com.hcy.core.model.FormIdDO;
import com.hcy.core.service.FormIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: 简单DI年华
 * @Date: 19-3-3 23:03
 * @Description:
 */


@Service
public class FormIdServiceImpl implements FormIdService {

    @Autowired
    FormIdMapper formIdMapper;

    @Override
    public String findFormIdByUserId(int userid) {
        FormIdDO formIdByUserId = formIdMapper.findFormIdByUserId(userid);
       if (formIdByUserId == null){
           return "null";
       }else {
           formIdMapper.deleteFormIdById(formIdByUserId.getId());
           return formIdByUserId.getFormid();
       }
    }

    @Override
    public void insertFormId(FormIdDO data) {
        formIdMapper.insertFormId(data);
    }
}
