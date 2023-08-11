package org.example.service;

import org.example.service.aop.AopService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AopServiceTest {

    @Resource
    private AopService aopService;

    @Test
    public void test() {
        aopService.test();
    }

}
