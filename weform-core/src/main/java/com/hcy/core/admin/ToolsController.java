package com.hcy.core.admin;

import com.hcy.core.common.response.RespCode;
import com.hcy.core.common.response.ResponseEntity;
import com.hcy.core.mapper_read.FormReadMapper;
import com.hcy.search.entity.FormPO;
import com.hcy.search.service.FormSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * @ClassName: ToolsController
 * @Author: hcy
 * @Description: 管理员工具集合
 * @Date: 2019-05-30 15:06
 * @Version: 1.0
 **/
@RestController
@RequestMapping("/admin")
public class ToolsController {

    @Autowired
    FormSearchService formSearchService;

    @Autowired
    FormReadMapper formReadMapper;

    @RequestMapping("/update/esdata")
    public ResponseEntity updateEsFormData(@RequestParam("nums") String nums){

        List<FormPO> topFormNums = formReadMapper.getTopFormNums(5);
        topFormNums.stream().forEach(formPO -> {
           formSearchService.insertFormPo(formPO);
        });

        return new ResponseEntity(RespCode.SUCCESS,topFormNums);

    }



}
