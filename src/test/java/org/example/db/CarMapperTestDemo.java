package org.example.db;

import org.example.entities.Car;
import org.example.mapper.CarMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
@Sql({"/db/schema-car.sql", "/db/data-car.sql"})
@ActiveProfiles("local")
public class CarMapperTestDemo {

    @Autowired
    CarMapper carMapper;

    @Test
    public void testSelectByMultiCondition() {
        // 假设三个条件都不为空
        List<Car> cars = carMapper.selectByMultiCondition("比亚迪", new BigDecimal("2.0"), "新能源");
        cars.forEach(car -> System.out.println(car));

        // 假设三个条件都为空
        cars = carMapper.selectByMultiCondition("", null, "");
        cars.forEach(car -> System.out.println(car));

        // 假设第一个不为空，后两个为空
        cars = carMapper.selectByMultiCondition("比亚迪", null, "");
        cars.forEach(car -> System.out.println(car));

        cars = carMapper.selectByMultiCondition("", new BigDecimal("2.0"), "新能源");
        cars.forEach(car -> System.out.println(car));

    }

    @Test
    public void testUpdateById() {
        Car car = new Car(1L, "比亚迪e2", "比亚迪", new BigDecimal("160000"), "新能源");
        int count = carMapper.updateById(car);
        System.out.println(count);
    }

    @Test
    public void testUpdateBySet() {
        Car car = new Car(2L, null, "奔驰C200", null, "新能源");
        int count = carMapper.updateBySet(car);
        System.out.println(count);

        car = new Car(2L, "比亚迪e3", "奔驰C200", BigDecimal.valueOf(136800L), "");
        carMapper.updateBySet(car);
    }

    @Test
    public void testInsertBatch() {
        // 准备数据
        Car car1 = new Car(null, "1200", "帕萨特1", new BigDecimal("30.0"), "燃油车");
        Car car2 = new Car(null, "1201", "帕萨特2", new BigDecimal("30.0"), "燃油车");
        Car car3 = new Car(null, "1202", "帕萨特3", new BigDecimal("30.0"), "燃油车");
        // 把数据添加到List集合当中
        List<Car> cars = new ArrayList<>();
        cars.add(car1);
        cars.add(car2);
        cars.add(car3);
        // 把集合传过去
        int count = carMapper.insertBatch(cars);
        System.out.println(count);
    }

    @Test
    public void testDeleteByIds() {
        // 准备一个数组
        Long[] ids = {6L, 7L, 8L};
        int count = carMapper.deleteByIds(ids);
        System.out.println(count);
    }

    @Test
    public void testIncludeQuery() {
        List<Car> cars = carMapper.selectAll();
        cars.forEach(car -> System.out.println(car));
    }

}
