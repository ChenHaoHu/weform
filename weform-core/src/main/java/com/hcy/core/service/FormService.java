package com.hcy.core.service;


import com.hcy.core.common.request.RequestForm;
import com.hcy.core.model.FormDO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Auther: 简单DI年华
 * @Date: 18-10-1 12:03
 * @Description: 操作表单的接口
 */

@Service
public interface FormService {

    String addForm(RequestForm requestForm);

    FormDO getFormByPassword(String password);

    String getFormTitle(Integer formid);

    List getFormByTag(String tag);

    String deleForm();

    String editForm();

    boolean checkForm(String password);

    Map getNum();

    String getPasswordByFormId(Integer formid);

    List getFormByUserid(Integer useid);

    List getFormByPage(Integer pageSize,Integer pageNum);

    List getActivityByPage(Integer pageSize,Integer pageNum);
}
