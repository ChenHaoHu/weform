package com.hcy.message.model;

/**
 * @Auther: 简单DI年华
 * @Date: 18-9-26 21:23
 * @Description: 用户信息的实体类
 */
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

    public Integer getuserid() {
        return userid;
    }

    public void setuserid(Integer userid) {
        this.userid = userid;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getIdentifytype() {
        return identifytype;
    }

    public void setIdentifytype(String identifytype) {
        this.identifytype = identifytype;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCollection() {
        return collection;
    }

    public void setCollection(String collection) {
        this.collection = collection;
    }

    public String getJoin() {
        return join;
    }

    public void setJoin(String join) {
        this.join = join;
    }

    public String getBuild() {
        return build;
    }

    public void setBuild(String build) {
        this.build = build;
    }

    public String getShare() {
        return share;
    }

    public void setShare(String share) {
        this.share = share;
    }

    public Integer getSigndaynum() {
        return signdaynum;
    }

    public void setSigndaynum(Integer signdaynum) {
        this.signdaynum = signdaynum;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

}

