package com.hcy.search.repos;

import com.hcy.search.entity.FormPO;
import org.elasticsearch.action.update.UpdateRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.UpdateQuery;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FormSearchRepositoryTest {

    @Autowired
    FormSearchRepository formSearchRepository;

    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;

    @Test
    public void test(){

        FormPO formPO = new FormPO(10002,"辽宁石油化工大学校园歌手","在辽石化校园选拔歌手","你好呀");
        formSearchRepository.save(formPO);
        formPO = new FormPO(10003,"河北王者荣耀","在河北王者荣耀选拔歌手","胡晨阳");
        formSearchRepository.save(formPO);
        formPO = new FormPO(10003,"安徽歌手","安徽歌手，安徽王者","胡晨阳");
        formSearchRepository.save(formPO);


        Pageable pagebale = new PageRequest(0,20);
        Page<FormPO> res = formSearchRepository.findDistinctFormPOByFormTitleIsLikeOrFormIntroIsLikeOrUserNameIsLike("安徽","安徽","安徽",pagebale);

//        System.out.println(res.getSize());
//        res = formSearchRepository.findAll(pagebale);

        for (FormPO f : res.getContent()){
            System.out.println(f);
        }
        UpdateQuery updateQuery = new UpdateQuery();
        updateQuery.setIndexName("form");
        updateQuery.setType("FormPO");
        updateQuery.setId(String.valueOf(2));
        UpdateRequest updateRequest = new UpdateRequest();
        Map map = new HashMap();
        map.put("formTitle","aaa");
        updateRequest.doc(map);
        updateQuery.setUpdateRequest(updateRequest);

        elasticsearchTemplate.update(updateQuery);


         pagebale = new PageRequest(0,20);
        res = formSearchRepository.findDistinctFormPOByFormTitleIsLikeOrFormIntroIsLikeOrUserNameIsLike("安徽","安徽","安徽",pagebale);


//        System.out.println(res.getSize());
//        res = formSearchRepository.findAll(pagebale);

        for (FormPO f : res.getContent()){
            System.out.println(f);
        }



    }




}