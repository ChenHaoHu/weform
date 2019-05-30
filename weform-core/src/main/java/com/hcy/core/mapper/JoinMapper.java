package com.hcy.core.mapper;

import com.hcy.core.model.JoinDO;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Auther: 简单DI年华
 * @Date: 18-10-3 23:13
 * @Description: 报名操作DAO
 */

@Mapper
@Component(value = "joinMapper")
public interface JoinMapper {

    //报名操作
    @Insert("INSERT INTO joinlist(formid,userid,content,time,isdelete)VALUES(" +
            "#{data.formid},#{data.userid},#{data.content},#{data.time},#{data.isdelete});")
    @Options(useGeneratedKeys = true, keyProperty = "data.id")
    Integer addJoinData(@Param("data") JoinDO join);

    //查找操作
    @Select("SELECT * FROM joinlist WHERE formid = #{formid} AND isdelete = 0")
    List<JoinDO> getJoinDataByFormid(@Param("formid")Integer formid);

    //查找formid操作
    @Select("SELECT formid FROM joinlist WHERE id = #{id}")
    Integer getFormidById(@Param("id")String id);


    //删除操作
    @Select("UPDATE  joinlist SET isdelete = 1 WHERE id = #{id}")
    Integer deleteDataById(@Param("id")String id);

    //根据formid查找所有人员操作
    @Select("SELECT userid FROM joinlist WHERE formid = #{formid} AND isdelete = 0 ")
    List<String> getUseridsById(@Param("formid")Integer formid);


    //根据userid查询自己报名的表单
    @Select("SELECT * FROM joinlist WHERE  userid = #{userid} AND isdelete = 0 ;")
    List<JoinDO> getJoinlistByUserid(@Param("userid") Integer userid);

}
