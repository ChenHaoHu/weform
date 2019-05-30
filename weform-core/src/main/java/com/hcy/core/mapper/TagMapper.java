package com.hcy.core.mapper;

import com.hcy.core.model.TagDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @Auther: 简单DI年华
 * @Date: 18-10-5 09:08
 * @Description: 标签操作DAO
 */

@Mapper
@Component(value = "tagMapper")
public interface TagMapper {

    //查找所有
    @Select("SELECT * FROM tags")
    List<TagDO> getAllTags();

    @Select("SELECT name,num FROM tags")
    List<Map> getAllTag();

    //数目加一
    @Update("UPDATE tags SET num = num +1 WHERE name = #{name};")
    void updateTagsNum(String name);


}
