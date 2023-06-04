package org.example.controller.trans.service;

import org.example.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @className: TransNameImpl
 * @author ryan
 * @CreateTime: 2022-10-17  13:54
 * @description TODO
 * @version 1.0
 */
@Service
public class TransNameImpl implements TransName{

    @Autowired
    AccountMapper accountMapper;

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public void updateName(){
        System.out.println("更新名字");
        accountMapper.updateName("zz");
        int index = 10 / 0;
    }
}
