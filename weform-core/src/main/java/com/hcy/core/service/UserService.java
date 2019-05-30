package com.hcy.core.service;

import com.hcy.core.model.UserDO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: 简单DI年华
 * @Date: 18-9-27 19:02
 * @Description: 用户处理接口
 */

@Service
public interface UserService {


    Integer getUseidByOpenid(String code);

    Integer addUser(String name, String code, String gender, String avatar,
                    String province, String city, String country);

    List<UserDO> getAllUsers();

    UserDO getUserByUserid();



}
