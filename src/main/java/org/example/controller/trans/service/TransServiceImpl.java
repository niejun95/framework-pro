package org.example.controller.trans.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @className TransServiceImpl
 * @author ryan
 * @createTime 2022-07-25  15:40
 * @description 事务测试
 * @version 1.0
 */
@Service
public class TransServiceImpl implements TransService{

    @Autowired
    TransName transName;

    @Autowired
    TransCreateTime transCreateTime;

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void updateData() {
        try {
            transName.updateName();
        } catch (Exception e) {
            e.printStackTrace();
        }
        transCreateTime.updateCreateTime();
    }
}
