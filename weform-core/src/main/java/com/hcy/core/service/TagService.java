package com.hcy.core.service;

import com.hcy.core.model.TagDO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: 简单DI年华
 * @Date: 18-10-5 09:13
 * @Description: 标签处理接口
 */

@Service
public interface TagService {

    List<TagDO> finAllTags();
}
