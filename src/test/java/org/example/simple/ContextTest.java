package org.example.simple;

import org.example.entities.Account;
import org.example.mapper.AccountMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ContextTest {

    @Autowired
    ApplicationContext context;

    @Test
    public void test() {
        AccountMapper accountMapper = context.getBean("accountMapper", AccountMapper.class);
        Account account = accountMapper.queryAccountInfoByName("ryan");
        System.out.println(account);
    }
}
