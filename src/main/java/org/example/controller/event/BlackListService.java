package org.example.controller.event;

import lombok.extern.slf4j.Slf4j;
import org.example.entity.Account;
import org.example.mapper.AccountMapper;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Slf4j
public class BlackListService {

    private AccountMapper accountMapper;

    private BlackListPublisher publisher;

    public BlackListService(AccountMapper accountMapper, BlackListPublisher publisher) {
        this.accountMapper = accountMapper;
        this.publisher = publisher;
    }

    public void handle(String phone) {
        Account account = accountMapper.queryByPhone(phone);
        if (Objects.isNull(account)) {
            log.info("数据库中没有这个账户");
            publisher.publisher(phone);
            return;
        }
    }
}
