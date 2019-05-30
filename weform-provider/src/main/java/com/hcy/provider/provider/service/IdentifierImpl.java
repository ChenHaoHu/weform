package com.hcy.provider.provider.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.hcy.Identifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ClassName: IdentifierImpl
 * @Author: hcy
 * @Description:
 * @Date: 2019-05-17 19:49
 * @Version: 1.0
 **/

@Service(interfaceClass = Identifier.class)
@Component
public class IdentifierImpl implements Identifier {


    @Autowired
    SnowFlake snowFlake;

    @Override
    public String getIdentifier(long dataCenterId, long machineId) {
        long l = snowFlake.nextId(dataCenterId, machineId);
        String s = NumericConvertUtils.toOtherNumberSystem(l, 62);
        return s;
    }
}
