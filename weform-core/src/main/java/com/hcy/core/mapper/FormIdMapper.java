package com.hcy.core.mapper;

import com.hcy.core.model.FormIdDO;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

/**
 * @Auther: 简单DI年华
 * @Date: 19-3-3 22:53
 * @Description:
 */
@Mapper
@Component(value = "formIdMapper")
public interface FormIdMapper {

    @Select("SELECT * FROM formid WHERE userid = #{userid} limit 1")
    FormIdDO findFormIdByUserId(@Param("userid")int userid);

    @Delete("DELETE  FROM formid WHERE id = #{id} ")
    int deleteFormIdById(@Param("id")int id);

    @Insert("INSERT INTO formid(userid,formid)VALUES(#{data.userid},#{data.formid})")
    int insertFormId(@Param("data")FormIdDO data);

}
