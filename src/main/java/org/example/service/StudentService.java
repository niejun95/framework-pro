package org.example.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.AllArgsConstructor;
import org.example.entities.Student;
import org.example.mapper.StudentMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class StudentService {

    private final StudentMapper studentMapper;


    public List<Student> getAllStudents() {
        return studentMapper.selectList(null);
    }


    public void addStudent(Student student) {
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email", student.getEmail());
        boolean exists = studentMapper.exists(queryWrapper);
        if (exists) {
            throw new RuntimeException("学生已存在");
        }
        int result = studentMapper.insert(student);
        if (result != 1) {
            throw new RuntimeException("新增学生失败");
        }
    }

    public void deleteAllStudent() {
        studentMapper.delete(null);
    }


    public Student getStudentById(Long id) {
        return studentMapper.selectById(id);
    }
}
