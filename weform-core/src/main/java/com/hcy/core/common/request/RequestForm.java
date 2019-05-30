package com.hcy.core.common.request;

/**
 * @Auther: 简单DI年华
 * @Date: 18-10-1 12:08
 * @Description: 请求实体类
 */

public class RequestForm {

    Integer userid;
    String title;
    String intro;
    String type;
    String mode;
    String start;
    String end;
    Integer maxnum;
    String iconurl;
    String posterurl;
    String ispublic;
    String username;
    String tags;


    public RequestForm() {
    }

    public RequestForm(Integer userid, String title, String intro, String type, String mode, String start, String end, Integer maxnum, String iconurl, String posterurl, String ispublic, String username, String tags) {
        this.userid = userid;
        this.title = title;
        this.intro = intro;
        this.type = type;
        this.mode = mode;
        this.start = start;
        this.end = end;
        this.maxnum = maxnum;
        this.iconurl = iconurl;
        this.posterurl = posterurl;
        this.ispublic = ispublic;
        this.username = username;
        this.tags = tags;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public Integer getMaxnum() {
        return maxnum;
    }

    public void setMaxnum(Integer maxnum) {
        this.maxnum = maxnum;
    }

    public String getIconurl() {
        return iconurl;
    }

    public void setIconurl(String iconurl) {
        this.iconurl = iconurl;
    }

    public String getPosterurl() {
        return posterurl;
    }

    public void setPosterurl(String posterurl) {
        this.posterurl = posterurl;
    }

    public String getIspublic() {
        return ispublic;
    }

    public void setIspublic(String ispublic) {
        this.ispublic = ispublic;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }
}
