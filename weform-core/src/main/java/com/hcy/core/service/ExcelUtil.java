package com.hcy.core.service;

import org.springframework.stereotype.Service;

/**
 * @Auther: 简单DI年华
 * @Date: 18-10-4 10:52
 * @Description: Excel操作工具
 */

@Service
public interface ExcelUtil {
     void init();
     String export(String formid);
}
