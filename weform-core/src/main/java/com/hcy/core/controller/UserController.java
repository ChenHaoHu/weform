package com.hcy.core.controller;

import com.hcy.core.common.response.RespCode;
import com.hcy.core.common.response.ResponseEntity;
import com.hcy.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: 简单DI年华
 * @Date: 18-9-27 00:31
 * @Description:
 */

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;



    @RequestMapping(value="/login",method= RequestMethod.GET)
    public ResponseEntity getUser(@RequestParam("name") String name,
                                  @RequestParam("code") String code,
                                  @RequestParam("gender") String gender,
                                  @RequestParam("avatar") String avatar,
                                  @RequestParam("province") String province,
                                  @RequestParam("city") String city,
                                  @RequestParam("country") String country) {

        //插入用户
        Integer userid = userService.addUser(name, code, gender, avatar, province, city, country);

        return  new ResponseEntity(RespCode.SUCCESS,userid);
    }

}
