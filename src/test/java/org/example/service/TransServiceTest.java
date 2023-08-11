package org.example.service;

import org.example.service.trans.TransService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TransServiceTest {

    @Resource
    private TransService transService;

    @Test
    public void testInsertTestNoRollbackFor() {
        transService.insertTestNoRollbackFor();
    }

    @Test
    public void testInsertTestRollbackFor() {
        transService.insertTestRollbackFor();
    }

    @Test
    public void testInsertTest() {
        transService.insertTest();
    }
}
