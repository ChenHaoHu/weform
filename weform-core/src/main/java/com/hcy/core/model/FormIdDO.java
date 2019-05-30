package com.hcy.core.model;
/**
 * @Auther: 简单DI年华
 * @Date: 19-2-27 23:28
 * @Description: formid
 */
public class FormIdDO {

    private int id;
    private int userid;
    private String formid;

    public FormIdDO() {
    }

    public FormIdDO(int userid, String formid) {
        this.userid = userid;
        this.formid = formid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getFormid() {
        return formid;
    }

    public void setFormid(String formid) {
        this.formid = formid;
    }

    @Override
    public String toString() {
        return "FormIdDO{" +
                "id=" + id +
                ", userid=" + userid +
                ", formid='" + formid + '\'' +
                '}';
    }
}
