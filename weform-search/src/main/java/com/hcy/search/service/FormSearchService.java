package com.hcy.search.service;

import com.hcy.search.entity.FormPO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: FormService
 * @Author: hcy
 * @Description: es表单搜索
 * @Date: 2019-05-30 08:39
 * @Version: 1.0
 **/

@Service
public interface FormSearchService {
    List<FormPO> findDistinctFormPOByFormTitleIsLikeOrFormIntroIsLike(String str, int page, int size);
    boolean updateFormPoIntro(int formId,String intro);
    boolean updateFormPoTitle(int formId,String title);
    boolean insertFormPo(FormPO formPO);
}
