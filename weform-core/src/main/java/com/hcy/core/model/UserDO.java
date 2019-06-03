package com.hcy.core.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @Auther: 简单DI年华
 * @Date: 18-9-26 21:23
 * @Description: 用户信息的实体类
 */
@Data
public class UserDO {


    //用户id
    private Integer userid;
    //用户openid 唯一标识
    private String openid;
    //用户昵称  初始化为微信昵称
    private String name;
    //用户头像
    private String avatar;
    //用户性别
    private String gender;
    //用户省份
    private String province;
    //所在城市
    private String city;
    //所在国家
    private String country;
    //用户验证身份的方式
    private String identifytype;
    //用户电话
    private String phone;
    //用户邮箱
    private String email;
    //用户首次注册时间
    private String time;
    //用户收藏的列表
    private String collection;
    //用户加入的列表
    private String join;
    //用户创建的列表
    private String build;
    //用户分享的文章
    private String share;
    //用户签到天数
    private Integer signdaynum;
    //用户等级
    private String grade;

    public UserDO() {
    }

    public UserDO(String openid, String name, String avatar, String gender, String province, String city, String country) {
        this.openid = openid;
        this.name = name;
        this.avatar = avatar;
        this.gender = gender;
        this.province = province;
        this.city = city;
        this.country = country;
    }

    public UserDO(Integer userid, String openid, String name, String avatar, String gender, String province, String city, String country, String identifytype, String phone, String email, String time, String collection, String join, String build, String share, Integer signdaynum, String grade) {
        this.userid = userid;
        this.openid = openid;
        this.name = name;
        this.avatar = avatar;
        this.gender = gender;
        this.province = province;
        this.city = city;
        this.country = country;
        this.identifytype = identifytype;
        this.phone = phone;
        this.email = email;
        this.time = time;
        this.collection = collection;
        this.join = join;
        this.build = build;
        this.share = share;
        this.signdaynum = signdaynum;
        this.grade = grade;
    }


}

