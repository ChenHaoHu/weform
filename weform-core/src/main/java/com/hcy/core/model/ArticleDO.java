package com.hcy.core.model;

/**
 * @Auther: 简单DI年华
 * @Date: 18-9-26 20:18
 * @Description: 用户分享文章的实体类
 */
public class ArticleDO {

    //文章id
    private Integer id;
    //文章标题
    private String title;
    //文章内容
    private String content;
    //作者id
    private Integer userid;
    //创建时间
    private String time;
    //点赞列表
    private Integer stars;
    //标签列表
    private String tags;
    //简介
    private String intro;
    //链接列表
    private String link;
    //头像
    private String iconurl;

    public ArticleDO() {
    }

    public ArticleDO(String title, String content, Integer userid, String tags, String intro, String link, String iconurl) {
        this.title = title;
        this.content = content;
        this.userid = userid;
        this.tags = tags;
        this.intro = intro;
        this.link = link;
        this.iconurl = iconurl;
    }

    public ArticleDO(Integer id, String title, String content, Integer userid, String time, Integer stars, String tags, String intro, String link, String iconurl) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.userid = userid;
        this.time = time;
        this.stars = stars;
        this.tags = tags;
        this.intro = intro;
        this.link = link;
        this.iconurl = iconurl;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getIconurl() {
        return iconurl;
    }

    public void setIconurl(String iconurl) {
        this.iconurl = iconurl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getstars() {
        return stars;
    }

    public void setstars(Integer stars) {
        this.stars = stars;
    }
}
