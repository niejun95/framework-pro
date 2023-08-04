package org.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.example.entities.Account;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Mapper
@Repository
public interface AccountMapper extends BaseMapper<Account> {
    Account queryAccountInfoByName(String name);

    List<Account> queryAccountByTime(LocalDate date);

    void updateLocalDate(LocalDate date);

    void updateDate(Date date);

    void insertSingle(Account account);

    void insertMany(List<Account> list);

    int deleteAccount(int id);

    void testIf(Account account);

    int updateName(String name);

    Account queryAccountInfoByNameForResultMap(String name);

    List<Account> queryAll();

    Account queryByPhone(String phone);

    @Select("select * from account where create_time >= #{startDate}")
    List<Account> queryGtTime(@Param("startDate") Date date);
}
