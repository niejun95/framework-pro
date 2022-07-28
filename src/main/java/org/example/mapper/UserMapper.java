package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.entities.AnotherUser;
import org.example.entities.User;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface UserMapper {
    List<AnotherUser> queryUserByCreateTime(Map map);
}
