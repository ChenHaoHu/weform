package com.hcy.message.mapper;

import com.hcy.message.model.UserDO;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Auther: 简单DI年华
 * @Date: 18-9-27 00:06
 * @Description: user表的DAO层
 */

@Mapper
@Component(value = "userMapper")
public interface UserMapper {

    //增加用户
    @Insert("INSERT INTO user(openid,name,avatar,gender,province,city,country,time)" +
            " VALUES(#{user.openid},#{user.name},#{user.avatar}," +
            "#{user.gender},#{user.province},#{user.city},#{user.country},#{user.time});")
    @Options(useGeneratedKeys = true, keyProperty = "user.userid")
    int insertUser(@Param("user") UserDO user);

    //通过openid寻找用户
    @Select("SELECT userid FROM user WHERE openid = #{openid}")
    Integer[] findUseridByOpenid(@Param("openid") String openid);


    //通过userid寻找用户
    @Select("SELECT * FROM user WHERE userid = #{userid} LIMIT 1")
    UserDO findUserByUserid(@Param("userid") String userid);


    @Select("SELECT name FROM user WHERE userid = #{userid} LIMIT 1")
    String findUserNameByUserid(@Param("userid") Integer userid);

    @Select("SELECT email FROM user WHERE userid = #{userid} LIMIT 1")
    String findUserMailByUserId(@Param("userid") Integer userid);

    @Select("SELECT phone FROM user WHERE userid = #{userid} LIMIT 1")
    String findUserPhoneByUserId(@Param("userid") Integer userid);

    @Select("SELECT name,email,phone FROM user WHERE userid = #{userid} LIMIT 1")
    Map<String,String> findUserNameAndEmailAndPhoneByUserId(@Param("userid") Integer userid);
}
