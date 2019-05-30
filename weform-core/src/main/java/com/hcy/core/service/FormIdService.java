package com.hcy.core.service;
import com.hcy.core.model.FormIdDO;
import org.springframework.stereotype.Service;

/**
 * @Auther: 简单DI年华
 * @Date: 19-3-3 23:01
 * @Description:
 */

@Service
public interface FormIdService {
    String findFormIdByUserId(int userid);
    void insertFormId(FormIdDO data);
}
