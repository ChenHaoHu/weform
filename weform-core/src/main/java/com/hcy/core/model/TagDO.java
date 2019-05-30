package com.hcy.core.model;

/**
 * @Auther: 简单DI年华
 * @Date: 18-10-5 09:06
 * @Description: 标签的实体类
 */
public class TagDO {

    private Integer id;
    private String name;
    //已有项目
    private Integer num;

    public TagDO() {
    }

    public TagDO(Integer id, String name, Integer num) {
        this.id = id;
        this.name = name;
        this.num = num;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
