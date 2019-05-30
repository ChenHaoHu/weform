package com.hcy.core.controller;


import com.hcy.core.common.response.RespCode;
import com.hcy.core.common.response.ResponseEntity;
import com.hcy.core.model.TagDO;
import com.hcy.core.service.ArticleService;
import com.hcy.core.service.FormService;
import com.hcy.core.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Auther: 简单DI年华
 * @Date: 18-10-5 09:11
 * @Description: 标签操作接口
 */

@RestController
@RequestMapping("/tag")
public class TagController {



    @Autowired
    TagService tagService;
    @Autowired
    ArticleService articleService;
    @Autowired
    FormService formService;



    @RequestMapping(value="/all",method= RequestMethod.GET)
    public ResponseEntity findAllTags() {

        List<TagDO> tags = tagService.finAllTags();

        return  new ResponseEntity(RespCode.SUCCESS,tags);
    }


    @RequestMapping(value="/value",method= RequestMethod.GET)
    public ResponseEntity findAllByTags(String tag) {

        List articleByTag = articleService.getArticleByTag(tag);
        List formByTag = formService.getFormByTag(tag);

        articleByTag.addAll(formByTag);

        return  new ResponseEntity(RespCode.SUCCESS,articleByTag);
    }

}
