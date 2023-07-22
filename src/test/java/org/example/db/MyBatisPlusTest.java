package org.example.db;

import lombok.extern.slf4j.Slf4j;
import org.example.entities.Student;
import org.example.mapper.StudentMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class MyBatisPlusTest {

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
}
