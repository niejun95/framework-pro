package org.example.service.impl;

import org.example.entities.Account;
import org.example.mapper.AccountMapper;
import org.example.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountMapper accountMapper;

    @Override
    public Account queryAccountInfoByNameForResultMap(String name) {
        return accountMapper.queryAccountInfoByNameForResultMap(name);
    }
}
