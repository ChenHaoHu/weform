package com.hcy.core.controller;



import com.hcy.core.common.response.RespCode;
import com.hcy.core.common.response.ResponseEntity;
import com.hcy.core.model.ArticleDO;
import com.hcy.core.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: 简单DI年华
 * @Date: 18-10-5 11:22
 * @Description:
 */

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    ArticleService articleService;




    @RequestMapping(value="/add",method= RequestMethod.GET)
    public ResponseEntity insertArticle(@RequestParam("title") String title,
                                        @RequestParam("content") String content,
                                        @RequestParam("userid") Integer userid,
                                        @RequestParam("intro") String intro,
                                        @RequestParam("tags") String tags,
                                        @RequestParam("link") String link,
                                        @RequestParam("iconurl") String iconurl) {


       Integer back =  articleService.addArticle(title,content, userid,intro,tags,link,iconurl);


        return  new ResponseEntity(RespCode.SUCCESS,back);
    }


    @RequestMapping(value="/find",method= RequestMethod.GET)
    public ResponseEntity insertArticle(@RequestParam("id") Integer id
                                       ) {

        Map articleById = articleService.getArticleById(id);

        return  new ResponseEntity(RespCode.SUCCESS,articleById);
    }



    @RequestMapping(value="/user/article",method= RequestMethod.GET)
    public ResponseEntity getArticleByUserid(Integer userid) {
        List list = articleService.getArticleByUserid(userid);
        return  new ResponseEntity(RespCode.SUCCESS,list);
    }


    @RequestMapping(value="/zan",method= RequestMethod.GET)
    public ResponseEntity zanArticle(Integer id) {
        articleService.zanArticle(id);
        return  new ResponseEntity(RespCode.SUCCESS,true);
    }


    @RequestMapping(value="/page",method= RequestMethod.GET)
    public ResponseEntity getArticleByPages(Integer pageSize,Integer pageNum) {
        List<ArticleDO> articleByPage = articleService.getArticleByPage( pageSize, pageNum);
       Map map = new HashMap();
       map.put("data",articleByPage);
       map.put("pageNum",pageNum);
        map.put("pageSize",articleByPage.size());
        return  new ResponseEntity(RespCode.SUCCESS,map);
    }
}
