package com.hcy.search.repos;

import com.hcy.search.entity.FormPO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

public interface FormSearchRepository extends ElasticsearchRepository<FormPO, Integer> {
    Page<FormPO> findDistinctFormPOByFormTitleIsLikeOrFormIntroIsLikeOrUserNameIsLike(String title, String intro,String user, Pageable pageable);
    FormPO findFormPOByFormId(int formId);
}