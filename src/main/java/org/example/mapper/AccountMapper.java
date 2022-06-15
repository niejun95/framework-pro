package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.entities.Account;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AccountMapper {
    Account queryAccountInfoByName(String name);
}
