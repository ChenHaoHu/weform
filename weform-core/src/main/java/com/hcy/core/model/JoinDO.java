package com.hcy.core.model;

/**
 * @Auther: 简单DI年华
 * @Date: 18-9-27 00:03
 * @Description: 用户填写加入的数据表
 * */
public class JoinDO {

    /**
     *   `id` int(11) NOT NULL,
     *   `formid` int(11) NULL DEFAULT NULL,
     *   `userid` int(11) NULL DEFAULT NULL,
     *   `content` json NULL,
     *   `time` varchar(0) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
     */
    //编号
    private Integer id;
    //表单的id
    private String formid;
    //用户id
    private String userid;
    //内容 json
    private String content;
    //加入时间
    private String time;
    //是否删除
    private boolean isdelete;

    public JoinDO() {
    }

    public JoinDO(String formid, String userid, String content, String time, boolean isdelete) {
        this.formid = formid;
        this.userid = userid;
        this.content = content;
        this.time = time;
        this.isdelete = isdelete;
    }

    public JoinDO(Integer id, String formid, String userid, String content, String time, boolean isdelete) {
        this.id = id;
        this.formid = formid;
        this.userid = userid;
        this.content = content;
        this.time = time;
        this.isdelete = isdelete;
    }

    public boolean isIsdelete() {
        return isdelete;
    }

    public void setIsdelete(boolean isdelete) {
        this.isdelete = isdelete;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFormid() {
        return formid;
    }

    public void setFormid(String formid) {
        this.formid = formid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
