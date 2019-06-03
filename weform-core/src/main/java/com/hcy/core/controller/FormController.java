package com.hcy.core.controller;


import com.hcy.core.common.request.RequestForm;
import com.hcy.core.common.response.RespCode;
import com.hcy.core.common.response.ResponseEntity;
import com.hcy.core.model.FormDO;
import com.hcy.core.service.FormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: 简单DI年华
 * @Date: 18-10-1 13:14
 * @Description: 表单操作接口
 */

@RestController
@RequestMapping("/form")
public class FormController {
    @Autowired
    FormService formService;


    @RequestMapping(value="/add",method= RequestMethod.GET)
    public ResponseEntity getUser(RequestForm data) {

        Integer userid = data.getUserid();


        data.setUserid(userid);

       String str =  formService.addForm(data);

        return  new ResponseEntity(RespCode.SUCCESS,str);
    }


    @RequestMapping(value="/find/password",method= RequestMethod.GET)
    @Cacheable("forms")
    public ResponseEntity getFormByPassword(String password) {

        password = password.toUpperCase();
        FormDO formByPassword = formService.getFormByPassword(password);

        return  new ResponseEntity(RespCode.SUCCESS,formByPassword);
    }


    @RequestMapping(value="/title",method= RequestMethod.GET)
    @Cacheable("forms")
    public ResponseEntity getTitleByFormid(Integer formid) {
        String formTitle = formService.getFormTitle(formid);
        return  new ResponseEntity(RespCode.SUCCESS,formTitle);
    }


    @RequestMapping(value="/check",method= RequestMethod.GET)
    public ResponseEntity checkForm(String password) {

        password = password.toUpperCase();

        boolean b = formService.checkForm(password);

        return  new ResponseEntity(RespCode.SUCCESS,b);
    }


    @RequestMapping(value="/password",method= RequestMethod.GET)
    public ResponseEntity getPasswordByFormTitle(Integer formid) {

        String passwordByFormId = formService.getPasswordByFormId(formid);

        return  new ResponseEntity(RespCode.SUCCESS,passwordByFormId);
    }


    @RequestMapping(value="/user/build",method= RequestMethod.GET)
    public ResponseEntity getFormByUserid(@RequestParam("userid")int userid) {

        List formByUserid =  formService.getFormByUserid(userid);
        return  new ResponseEntity(RespCode.SUCCESS,formByUserid);
    }


    @RequestMapping(value="/form/page",method= RequestMethod.GET)
    public ResponseEntity geFormByPages(Integer pageSize,Integer pageNum) {
        List<FormDO> formByPage = formService.getFormByPage( pageSize, pageNum);
        Map map = new HashMap();
        map.put("data",formByPage);
        map.put("pageNum",pageNum);
        map.put("pageSize",formByPage.size());
        return  new ResponseEntity(RespCode.SUCCESS,map);
    }


    @RequestMapping(value="/activity/page",method= RequestMethod.GET)
    public ResponseEntity getActivitryByPages(Integer pageSize,Integer pageNum) {
        List<FormDO> activityByPage = formService.getActivityByPage( pageSize, pageNum);
        Map map = new HashMap();
        map.put("data",activityByPage);
        map.put("pageNum",pageNum);
        map.put("pageSize",activityByPage.size());
        return  new ResponseEntity(RespCode.SUCCESS,map);
    }

}
