package org.example.db;

import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.example.entities.Student;
import org.example.mapper.StudentMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
@Sql("/db/schema-student.sql") // 执行之后不会删除 只有 h2 内存才会删除
public class StudentMapperTest {

    @Autowired
    private StudentMapper studentMapper;

    @Test
    public void testSelect() {
        log.info("---select all student");
        List<Student> userList = studentMapper.selectList(null);
        userList.forEach(System.out::println);
    }

    @Test
    public void insert() {
        Student student = new Student();
        student.setStudentName("lucy");
        student.setSex("0");
        student.setEmail("lucy@gmail.com");
        student.setAge(18);
        int result = studentMapper.insert(student);
        Assert.assertEquals(result, 1);
    }

    @Test
    @Sql({"/db/schema-student.sql", "/db/data-student.sql"}) // 会覆盖方法级别的脚本
    public void useSelectAnnotation() {
        List<Student> students = studentMapper.queryStudentAgeBigThanParam(28);
        students.forEach(student -> {
            log.info("student info {}", JSON.toJSONString(student));
        });
    }
}
