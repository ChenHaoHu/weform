package com.hcy.core.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.aliyun.oss.OSSClient;
import com.hcy.Identifier;
import com.hcy.core.service.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;

/**
 * @ClassName: CommonUtilImpl
 * @Author: hcy
 * @Description:
 * @Date: 2019-05-17 12:43
 * @Version: 1.0
 **/
@Service
public class CommonUtilImpl implements CommonUtil {

    @Value("${OSS.bucketName}")
    String bucketName;

    @Value("${OSS.endpoint}")
    String endpoint;

    @Value("${OSS.bucketUrl}")
    String bucketUrl;

    @Value("${base.dataCenterId}")
    long dataCenterId;

    @Value("${base.machineId}")
    long machineId;

    @Reference(check = false)
    Identifier identifier;


    @Autowired
    OSSClient ossClient;

    @Override
    public String uploadFile(String name, InputStream ins) {
        String[] filename = name.split("\\.");
        String s = getIdentifier(dataCenterId,  machineId)+"." + filename[filename.length - 1];
        try {
            ossClient.putObject(bucketName,s,ins);
        }catch (Exception e){
            System.out.println("upload fail");
            return null;
        }
        return bucketUrl+s;
    }

    @Override
    public String getIdentifier(long dataCenterId, long machineId) {
        String s = identifier.getIdentifier(dataCenterId,machineId);
        return s;
    }
}
