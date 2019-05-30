package com.hcy.core.mapper_read;
import com.hcy.core.model.FormDO;
import com.hcy.search.entity.FormPO;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Auther: 简单DI年华
 * @Date: 18-10-1 12:16
 * @Description: 表单DAO层
 */

@Mapper
@Component(value = "formReadMapper")
public interface FormReadMapper {




    //根据密匙获取信息
    @Select("SELECT * FROM form WHERE password = #{password} LIMIT 1;")
    FormDO getFromByPassword(@Param("password") String password);

    //根据formid获取title
    @Select("SELECT title FROM form WHERE formid = #{formid} LIMIT 1;")
     String getTitleByFormid(@Param("formid") String formid);

    //根据formid获取form
    @Select("SELECT * FROM form WHERE formid = #{formid} LIMIT 1;")
    List<FormDO> getFormByFormid(@Param("formid") String formid);

    //根据formid获取password
    @Select("SELECT password FROM form WHERE formid = #{formid} ;")
    String getPasswordByFormid(@Param("formid") Integer formid);

    //根据tag查找form
    @Select("SELECT * FROM form WHERE  tags = #{tag} AND ispublic = 'true' ")
    List<FormDO> getFormByTag(@Param("tag") String tag);


    //查找form数量
    @Select("SELECT COUNT(*) FROM form WHERE type = 'form' ")
    Integer getFormNum();

    //查找avtivity数量
    @Select("SELECT COUNT(*) FROM form WHERE type = 'activity' ")
    Integer getAvtivityNum();

    //根据formid获取password
    @Select("SELECT password FROM form WHERE formid = #{formid} LIMIT 1;")
    String getPasswordByFormId(@Param("formid") Integer formid);

    //根据userid查询自己创建的表单
    @Select("SELECT * FROM form WHERE  userid = #{userid};")
    List<FormDO> getFormByUserid(@Param("userid") Integer userid);

    //获取所有公开的Activity
    @Select("SELECT * FROM form WHERE ispublic = 'true' AND type = 'activity' ")
    List<FormDO> getAllActivity();

    //获取所有公开的调查问卷
    @Select("SELECT * FROM form WHERE ispublic = 'true' AND type = 'form' ")
    List<FormDO> getAllForm();

    @Select("select formid,title,intro,username from form  order by  formid desc  limit #{nums};")
    @Results({@Result(property="formId",column="formid")
             ,@Result(property="formTitle",column="title")
             ,@Result(property="formIntro",column="intro")
             ,@Result(property="userName",column="username")

    })
    List<FormPO> getTopFormNums(@Param("nums")int nums);

}
