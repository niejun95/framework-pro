package org.example.db;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.example.entities.Account;
import org.example.mapper.AccountMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class DateTimeTypeTest {
    @Autowired
    private AccountMapper accountMapper;

    @Test
    public void updateDateTest() {
        Account account = new Account();
        account.setId(1);
        account.setCreateTime(new Date());
        accountMapper.updateById(account);
    }


    @Test
    public void queryGtTime() {
        DateTime dateTime = DateUtil.parse("2023-08-01");
        Date param = new Date(dateTime.getTime());
        List<Account> accounts = accountMapper.queryGtTime(param);
        log.info("result : {} ", JSON.toJSONString(accounts));
    }

}

