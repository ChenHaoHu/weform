//package com.hcy.core.controller;
//
//import com.hcy.core.common.response.RespCode;
//import com.hcy.core.common.response.ResponseEntity;
//import com.hcy.search.entity.FormPO;
//import com.hcy.search.service.FormSearchService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
///**
// * @ClassName: testController
// * @Author: hcy
// * @Description: 搜索API
// * @Date: 2019-05-27 15:44
// * @Version: 1.0
// **/
//
//@RestController
//public class SearController {
//
//
//    @Autowired
//    FormSearchService formSearchService;
//
//    @PostMapping("/sear")
//    public ResponseEntity test(@RequestParam("text")String str) throws InterruptedException {
//        List<FormPO> list = formSearchService.findDistinctFormPOByFormTitleIsLikeOrFormIntroIsLike(str, 0, 20);
//        return new ResponseEntity(RespCode.SUCCESS,list);
//    }
//}
