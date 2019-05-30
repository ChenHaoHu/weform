package com.hcy.core.service;

import com.hcy.core.model.ArticleDO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Auther: 简单DI年华
 * @Date: 18-10-5 11:14
 * @Description:
 */

@Service
public interface ArticleService {

    List<ArticleDO> getArticleByPage(Integer pageSize, Integer pageNum);

    Map getArticleById(Integer id);

    Integer addArticle(String title, String content,Integer userid,String intro,String tags,
                       String link,String iconurl);

    List getArticleByTag(String tag);

    Integer getNum();

    List getExcellentArticle(Integer level);

    List getArticleByUserid(Integer userid);

    boolean zanArticle(Integer id);

}
