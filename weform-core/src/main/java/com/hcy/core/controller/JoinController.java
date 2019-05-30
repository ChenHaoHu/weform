package com.hcy.core.controller;


import com.hcy.core.common.response.RespCode;
import com.hcy.core.common.response.ResponseEntity;
import com.hcy.core.service.JoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Auther: 简单DI年华
 * @Date: 18-10-3 23:22
 * @Description: 报名操作接口
 */

@RestController
@RequestMapping("/api/join")
public class JoinController {

    @Autowired
    JoinService joinService;


    @RequestMapping(value="/form",method= RequestMethod.GET)
    public ResponseEntity joinForm(@RequestParam("formid")String formid,
                                  @RequestParam("content")String content,
                                   HttpServletRequest httpServletRequest
                                 ) {
        String userid =(String)httpServletRequest.getAttribute("userid");
        Integer back =  joinService.joinForm(formid,userid,content);
        return  new ResponseEntity(RespCode.SUCCESS,back);
    }



    @RequestMapping(value="/delete",method= RequestMethod.GET)
    public ResponseEntity deletejoinForm(@RequestParam("id")String id) {
        String back =  joinService.deleteJoinData(id);
        return  new ResponseEntity(RespCode.SUCCESS,back);
    }


    @RequestMapping(value="/password",method= RequestMethod.GET)
    public ResponseEntity getjoinpassword(@RequestParam("id")String id) {
        String back =  joinService.getJoinPassword(id);
        return  new ResponseEntity(RespCode.SUCCESS,back);
    }



    @RequestMapping(value="/export",method= RequestMethod.GET)
    public ResponseEntity exportExcel(@RequestParam("formid") Integer formid) {
        String back =  joinService.exportJoinData(formid);
        return  new ResponseEntity(RespCode.SUCCESS,back);
    }



    @RequestMapping(value="/user",method= RequestMethod.GET)
    public ResponseEntity getUserDataByFormid(@RequestParam("formid") Integer formid) {
        List back =  joinService.getUserDataByFormid(formid);
        return  new ResponseEntity(RespCode.SUCCESS,back);
    }


    @RequestMapping(value="/user/sign",method= RequestMethod.GET)
    public ResponseEntity getJoinByUserid( HttpServletRequest httpServletRequest) {
        Integer userid = Integer.valueOf((String)httpServletRequest.getAttribute("userid"));

        List list = joinService.getJoinlistByUserid(userid);
        return  new ResponseEntity(RespCode.SUCCESS,list);
    }

}
