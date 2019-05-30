package com.hcy.core.mapper;

import com.hcy.core.model.ArticleDO;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Auther: 简单DI年华
 * @Date: 18-10-5 11:18
 * @Description: 文章DAO
 */

@Mapper
@Component(value = "articleMapper")
public interface ArticleMapper {

    @Insert("INSERT INTO article(title,content,userid,time,tags,intro,link,iconurl) VALUES(" +
            "#{article.title},#{article.content},#{article.userid},#{article.time}" +
            ",#{article.tags},#{article.intro},#{article.link},#{article.iconurl})")
    @Options(useGeneratedKeys = true, keyProperty = "article.id")
    Integer insertArticle(@Param("article") ArticleDO article);


    //根据id获取文章
    @Select("SELECT * FROM article WHERE id = #{id} LIMIT 1;")
    ArticleDO getArticleById(@Param("id")Integer id);

    //根据tag查找文章

    /**
     * 这个方法是有问题的
     * 但是可以使用
     * 因为select * from article WHERE JSON_CONTAINS(tags, '["教育"]'这个语句存在单引号
     * 和双引号，直接常规写法无法实现查找，因为项目太赶，出此下策
     */
    @Select("${tag}")
    List<ArticleDO> getArticleByTag(@Param("tag")String tag);

    //查找所有Article
    @Select("SELECT * FROM article")
    List<ArticleDO> getAllArticle();


    //查找article数量
    @Select("SELECT COUNT(*) FROM article  ")
    Integer getArticleNum();

    //查找点赞数量超过 level 的分享
    @Select("SELECT * FROM article WHERE  stars > #{level} ORDER BY stars ")
    List<ArticleDO> getExcellentArticleByLevel(@Param("level")Integer level);

    //根据userid查询文章
    @Select("SELECT * FROM article WHERE  userid = #{userid} ;")
    List<ArticleDO> getArticleByUserid(@Param("userid") Integer userid);

    //分享赞数目加一
    @Update("UPDATE article SET stars = stars +1 WHERE id = #{id};")
    void updateZanNum(Integer id);

}
