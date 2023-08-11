package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.entity.Trader;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @description:
 * @author: ryan
 * @date 2023/6/9 16:01
 * @version: 1.0
 */
@Mapper
@Repository
public interface TraderMapper {

    public Trader queryById(Long id);

    public List<Trader> queryAll();

    public void simpleInsert(Trader trader);
}
