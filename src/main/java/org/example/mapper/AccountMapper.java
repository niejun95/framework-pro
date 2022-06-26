package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.entities.Account;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Mapper
@Repository
public interface AccountMapper {
    Account queryAccountInfoByName(String name);

    List<Account> queryAccountByTime(LocalDate date);

    void updateLocalDate(LocalDate date);

    void updateDate(Date date);

    void insertSingle(Account account);

    void insertMany(List<Account> list);

    int deleteAccount(int id);
}
