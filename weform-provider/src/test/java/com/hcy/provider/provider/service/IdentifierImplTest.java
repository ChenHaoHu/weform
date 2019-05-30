package com.hcy.provider.provider.service;

import com.hcy.Identifier;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IdentifierImplTest {


    @Autowired
    Identifier identifier;


    @Test
    public void getIdentifier() {
        String identifier = this.identifier.getIdentifier(1, 2);
        System.out.println(identifier);
    }
}