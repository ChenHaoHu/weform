package com.hcy.core.controller;



import com.hcy.core.common.response.RespCode;
import com.hcy.core.common.response.ResponseEntity;
import com.hcy.core.service.ArticleService;
import com.hcy.core.service.CommonUtil;
import com.hcy.core.service.FormService;
import com.hcy.core.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: 简单DI年华
 * @Date: 18-9-12 19:17
 * @Description:
 */
@RestController
@RequestMapping("/common")
public class CommonControler {



    @Autowired
    ArticleService articleService;
    @Autowired
    FormService formService;
    @Autowired
    TagService tagService;
    @Autowired
    CommonUtil commonUtil;


    /**
     * 获取图片信息
     * 获取图片信息的同时是保存图片在服务器 并以身份证号命名
     * @param file
     * @return
     */
    @PostMapping(value = "/upload")
    public ResponseEntity uploadFile(@RequestParam("file") MultipartFile file){
        String url = null;
        System.out.println("---------------");
        try {
             url = commonUtil.uploadFile(file.getOriginalFilename(), file.getInputStream());
            System.out.println(url);
        }catch (Exception e){
            return new ResponseEntity(RespCode.ERROR,null);
        }
       if (url!= null){
           Map<String,String> map = new HashMap<>();
           map.put("url",url);
           return new ResponseEntity(RespCode.SUCCESS,map);
       }else{
           return new ResponseEntity(RespCode.ERROR,null);
       }
    }





    @GetMapping(value = "/onload")
    public ResponseEntity onLoadHomePage(@RequestParam("level") Integer level){
        Map back = new HashMap();
        Map num = formService.getNum();
        num.put("article",articleService.getNum());
        back.put("num",num);
        back.put("good",articleService.getExcellentArticle(level));
        back.put("tags",tagService.finAllTags());
        return new ResponseEntity(RespCode.SUCCESS,back);
    }



}
