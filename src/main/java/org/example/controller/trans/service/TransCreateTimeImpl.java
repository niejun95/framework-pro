package org.example.controller.trans.service;

import org.example.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @className TransCreateTimeImpl
 * @author ryan
 * @createTime 2022-10-17  13:54
 * @description TODO
 * @version 1.0
 */
@Service
public class TransCreateTimeImpl implements TransCreateTime{

    @Autowired
    AccountMapper accountMapper;

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    @Override
    public void updateCreateTime() {
        System.out.println("更新时间");
        accountMapper.updateDate(new Date());
    }
}
