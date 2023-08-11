package org.example.controller.gerneral;

import org.example.entity.Account;
import org.example.mapper.AccountMapper;

/**
 * @className BatchInsertThread
 * @author niejun
 * @date 2022/6/21
 * @description
 * @version 1.0
 **/
public class BatchInsertThread implements Runnable{

    public AccountMapper accountMapper;

    public int index;

    public BatchInsertThread(int index, AccountMapper accountMapper) {
        this.index = index;
        this.accountMapper = accountMapper;
    }

    @Override
    public void run() {
        Account account = new Account();
        account.setId(index);
        account.setName("ryan" + index);
        account.setMoney((100 + index));
        accountMapper.insertSingle(account);
    }
}
