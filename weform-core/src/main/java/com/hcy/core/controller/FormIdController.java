package com.hcy.core.controller;


import com.hcy.core.common.response.RespCode;
import com.hcy.core.common.response.ResponseEntity;
import com.hcy.core.model.FormIdDO;
import com.hcy.core.service.FormIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Auther: 简单DI年华
 * @Date: 19-3-3 23:23
 * @Description:
 */

@RestController
@RequestMapping("/api")
public class FormIdController {

    @Autowired
    FormIdService formIdService;

    @PostMapping("/formid")
    public ResponseEntity insertFomid(String formId, HttpServletRequest httpServletRequest){
        Integer userid = Integer.valueOf((String)httpServletRequest.getAttribute("userid"));
        formIdService.insertFormId(new FormIdDO(userid,formId));
        return new ResponseEntity(RespCode.SUCCESS,"");
    }

    @GetMapping("/formid")
    public ResponseEntity getomid(HttpServletRequest httpServletRequest){
        Integer userid = Integer.valueOf((String)httpServletRequest.getAttribute("userid"));
        String formIdByUserId = formIdService.findFormIdByUserId(userid);
        return new ResponseEntity(RespCode.SUCCESS,formIdByUserId);
    }
}
