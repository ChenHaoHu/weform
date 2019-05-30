package com.hcy.search.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Mapping;

import java.io.Serializable;

@Document(indexName = "form", type = "FormPO")
@Data
public class FormPO implements Serializable {

    @Id
    @Field(index = false, store = true, type = FieldType.Integer)
    private Integer formId;
    @Field(analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
    private String formTitle;
    @Field( analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
    private String formIntro;
    @Field(analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
    private String userName;


    public FormPO() {
    }

    public FormPO(Integer formId, String formTitle, String formIntro, String userName) {
        this.formId = formId;
        this.formTitle = formTitle;
        this.formIntro = formIntro;
        this.userName = userName;
    }
}