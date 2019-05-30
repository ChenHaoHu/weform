package com.hcy.core.mapper;
import com.hcy.core.model.FormDO;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Auther: 简单DI年华
 * @Date: 18-10-1 12:16
 * @Description: 表单DAO层
 */

@Mapper
@Component(value = "formMapper")
public interface FormMapper {


    //增加表单
    @Insert("INSERT INTO form(userid,title,type,mode,start,end,maxnum,createtime,intro" +
            ",iconurl,posterurl,tags,ispublic,username)" +
            " VALUES(#{form.userid},#{form.title},#{form.type}," +
            "#{form.mode},#{form.start},#{form.end},#{form.maxnum},#{form.createtime}" +
            ",#{form.intro},#{form.iconurl},#{form.posterurl}" +
            ",#{form.tags},#{form.ispublic},#{form.username});")
    @Options(useGeneratedKeys = true, keyProperty = "form.formid")
     int insertform(@Param("form") FormDO form);

    //增加密匙
    @Update("UPDATE form SET password = #{password} WHERE formid = #{formid}")
     int addpassword(@Param("formid") Integer formid,
                           @Param("password") String password);

    //根据密匙获取信息
    @Select("SELECT * FROM form WHERE password = #{password} LIMIT 1;")
    FormDO getFromByPassword(@Param("password")String password);

    //根据formid获取title
    @Select("SELECT title FROM form WHERE formid = #{formid} LIMIT 1;")
     String getTitleByFormid(@Param("formid")String formid);

    //根据formid获取form
    @Select("SELECT * FROM form WHERE formid = #{formid} LIMIT 1;")
    List<FormDO> getFormByFormid(@Param("formid")String formid);

    //根据formid获取password
    @Select("SELECT password FROM form WHERE formid = #{formid} ;")
    String getPasswordByFormid(@Param("formid")Integer formid);



    //根据tag查找form
    @Select("SELECT * FROM form WHERE  tags = #{tag} AND ispublic = 'true' ")
    List<FormDO> getFormByTag(@Param("tag")String tag);


    //查找form数量
    @Select("SELECT COUNT(*) FROM form WHERE type = 'form' ")
    Integer getFormNum();

    //查找avtivity数量
    @Select("SELECT COUNT(*) FROM form WHERE type = 'activity' ")
    Integer getAvtivityNum();

    //根据formid获取password
    @Select("SELECT password FROM form WHERE formid = #{formid} LIMIT 1;")
    String getPasswordByFormId(@Param("formid")Integer formid);

    //根据userid查询自己创建的表单
    @Select("SELECT * FROM form WHERE  userid = #{userid};")
    List<FormDO> getFormByUserid(@Param("userid") Integer userid);

    //获取所有公开的Activity
    @Select("SELECT * FROM form WHERE ispublic = 'true' AND type = 'activity' ")
    List<FormDO> getAllActivity();

    //获取所有公开的调查问卷
    @Select("SELECT * FROM form WHERE ispublic = 'true' AND type = 'form' ")
    List<FormDO> getAllForm();


}
