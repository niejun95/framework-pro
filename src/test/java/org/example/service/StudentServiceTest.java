package org.example.service;

import org.example.entities.Student;
import org.example.mapper.StudentMapper;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class StudentServiceTest {

    private StudentService studentService;

    @Mock
    StudentMapper studentMapper;


    @BeforeEach
    void setUp() {
        studentService = new StudentService(studentMapper);

    }

    @Test
    void getAllStudents() {
        Student student = new Student();
        student.setId(1L);
        student.setSex("1");
        student.setStudentName("ryan");
        student.setEmail("1289680419@qq.com");
        student.setAge(28);
        List<Student> expected = Arrays.asList(student);
        when(studentMapper.selectList(null)).thenReturn(expected);

        List<Student> queryResult = studentService.getAllStudents();

        Assert.assertEquals(expected, queryResult);

    }

    @Test
    void addStudent() {
    }

    @Test
    void deleteAllStudent() {
    }

    @Test
    void getStudentById() {
        // 构造 mapper 查询的参数
        Long id = 1L;

        // 构造 mapper 执行的返回值
        Student student = new Student();
        student.setId(1L);
        student.setSex("1");
        student.setStudentName("ryan");
        student.setEmail("1289680419@qq.com");
        student.setAge(28);
        when(studentMapper.selectById(id)).thenReturn(student);

        // 执行 service 方法
        Student result = studentService.getStudentById(id);

        Assert.assertEquals(result, student);
    }

    @Test
    void getStudentById_mockEmpty() {
        Long id = 1L;

        when(studentMapper.selectById(id)).thenReturn(null);

        Student student = studentService.getStudentById(id);

        Student expected = new Student();
        expected.setId(1L);
        expected.setSex("1");
        expected.setStudentName("ryan");
        expected.setEmail("1289680419@qq.com");
        expected.setAge(28);

        Assert.assertEquals(student, expected);
    }
}