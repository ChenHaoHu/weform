package com.hcy.core.service;

import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.Map;

/**
 * @ClassName: CommonUtil
 * @Author: hcy
 * @Description:
 * @Date: 2019-05-17 12:41
 * @Version: 1.0
 **/
@Service
public interface CommonUtil {
    String uploadFile(String name, InputStream ins);
    String getIdentifier(long dataCenterId, long machineId);
}
