package com.hcy.search.service;

import com.hcy.search.entity.FormPO;
import com.hcy.search.repos.FormSearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName: FormServiceImpl
 * @Author: hcy
 * @Description:
 * @Date: 2019-05-30 08:43
 * @Version: 1.0
 **/

@Service
public class FormSearchServiceImpl implements FormSearchService {

    @Autowired
    FormSearchRepository formSearchRepository;




    @Override
    public List<FormPO> findDistinctFormPOByFormTitleIsLikeOrFormIntroIsLike(String str, int page, int size) {
        Pageable pagebale = new PageRequest(page,size);
        Page<FormPO> res = formSearchRepository.findDistinctFormPOByFormTitleIsLikeOrFormIntroIsLikeOrUserNameIsLike(str, str, str,pagebale);
        List<FormPO> content = res.getContent();
        return content;
    }

    @Override
    public boolean updateFormPoIntro(int formId, String intro) {
        FormPO formPOByFormId = formSearchRepository.findFormPOByFormId(formId);
        if (formPOByFormId != null){
            formPOByFormId.setFormIntro(intro);
            formSearchRepository.save(formPOByFormId);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateFormPoTitle(int formId, String title) {
        FormPO formPOByFormId = formSearchRepository.findFormPOByFormId(formId);
        if (formPOByFormId != null){
            formPOByFormId.setFormTitle(title);
            formSearchRepository.save(formPOByFormId);
            return true;
        }
        return false;
    }

    @Override
    public boolean insertFormPo(FormPO formPO) {
        formSearchRepository.save(formPO);
        return true;
    }
}
