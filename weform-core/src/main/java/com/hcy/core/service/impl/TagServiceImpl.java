package com.hcy.core.service.impl;


import com.hcy.core.mapper.TagMapper;
import com.hcy.core.model.TagDO;
import com.hcy.core.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: 简单DI年华
 * @Date: 18-10-5 09:14
 * @Description:
 */

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    TagMapper tagMapper;

    @Override
    public List<TagDO> finAllTags() {

        List data = tagMapper.getAllTags();

        return data;
    }
}
