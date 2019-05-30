package com.hcy;

import com.hcy.search.service.FormSearchService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WeformCoreApplicationTests {


    @Autowired
    FormSearchService formSearchService;

    @Test
    public void contextLoads() {

        System.out.println(formSearchService);


    }

}
