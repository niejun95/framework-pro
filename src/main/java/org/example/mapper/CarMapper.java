package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.entity.Car;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Mapper
@Repository
public interface CarMapper {
    List<Car> selectByMultiCondition(@Param("brand") String brand, @Param("guidePrice") BigDecimal guidePrice, @Param("carType") String carType);

    int updateById(Car car);

    int updateBySet(Car car);

    // 批量插入，一次插入多条Car信息，foreach标签
    int insertBatch(@Param("cars") List<Car> cars);

    // 批量删除，foreach标签
    int deleteByIds(@Param("ids") Long[] ids);

    List<Car> selectAll();
}
