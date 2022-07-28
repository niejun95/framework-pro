package org.example.controller;

import org.example.entities.Account;
import org.example.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @ClassName: Trans
 * @Author: niejun
 * @CreateTime: 2022-07-25  15:40
 * @Description: 事务测试
 * @Version: 1.0
 */
@Service
public class TransTest {
    @Autowired
    AccountMapper accountMapper;

    @Transactional(rollbackFor = Exception.class)
    public void change(Account account) throws Exception{
        accountMapper.updateDate(new Date());

        throw new Exception("手动回滚");
    }
}
