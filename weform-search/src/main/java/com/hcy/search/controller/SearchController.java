package com.hcy.search.controller;

import com.hcy.search.entity.FormPO;
import com.hcy.search.response.RespCode;
import com.hcy.search.response.ResponseEntity;
import com.hcy.search.service.FormSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName: SearchController
 * @Author: hcy
 * @Description:
 * @Date: 2019-05-30 08:47
 * @Version: 1.0
 **/

@RestController
public class SearchController {

    @Autowired
    FormSearchService formSearchService;

    @RequestMapping("/sear")
    public ResponseEntity findSearRes(@RequestParam("text")String text,
                                      @RequestParam("page")int page,
                                      @RequestParam("size")int size){
        List<FormPO> formPOS = formSearchService.findDistinctFormPOByFormTitleIsLikeOrFormIntroIsLike(text, page, size);
        return new ResponseEntity(RespCode.SUCCESS,formPOS);
    }

    @RequestMapping("/update/intro")
    public ResponseEntity updateIntro(@RequestParam("form")int formId,
                                      @RequestParam("text")String text){
        boolean formPOS = formSearchService.updateFormPoIntro(formId,text);
        return new ResponseEntity(RespCode.SUCCESS,formPOS);
    }

    @RequestMapping("/update/title")
    public ResponseEntity updateTitle(@RequestParam("form")int formId,
                                      @RequestParam("text")String text){
        boolean formPOS = formSearchService.updateFormPoTitle(formId,text);
        return new ResponseEntity(RespCode.SUCCESS,formPOS);
    }

    @RequestMapping("/insert")
    public ResponseEntity insertForm(@RequestParam("form")int formId,
                                      @RequestParam("title")String title,
                                      @RequestParam("intro")String intro,
                                      @RequestParam("user")String user){
        boolean formPOS = formSearchService.insertFormPo(new FormPO(formId,title,intro,user));
        return new ResponseEntity(RespCode.SUCCESS,formPOS);
    }

}
