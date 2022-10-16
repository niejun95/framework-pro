package org.example.controller.trans;

import org.example.mapper.AccountMapper;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @ClassName: TransServiceImpl
 * @Author: ryan
 * @CreateTime: 2022-07-25  15:40
 * @Description: 事务测试
 * @Version: 1.0
 */
@Service
public class TransServiceImpl implements TransService{

    @Autowired
    AccountMapper accountMapper;

    @Transactional(rollbackFor = Exception.class)
    public void updateCreateTime() {
        System.out.println(this);
        accountMapper.updateDate(new Date());
        TransServiceImpl proxy = (TransServiceImpl) AopContext.currentProxy();
        System.out.println("address:" + proxy + " class:" + proxy.getClass());
        proxy.updateName();
        int index = 10 / 0;
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public void updateName(){
        accountMapper.updateName("zz");
    }

}
