package org.example.service.trans;

import lombok.RequiredArgsConstructor;
import org.example.entity.Student;
import org.example.mapper.StudentMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TransService {

    private final StudentMapper studentMapper;

    @Transactional(rollbackFor = Exception.class, noRollbackFor = RuntimeException.class)
    public void insertTestNoRollbackFor() {
        Student student = new Student();
        student.setSex("1");
        student.setAge(28);
        student.setEmail("1289680419@qq.com");
        student.setStudentName("niejun");
        studentMapper.insert(student);
        throw new RuntimeException();
    }

    @Transactional(rollbackFor = Exception.class, noRollbackFor = RuntimeException.class)
    public void insertTestRollbackFor() {
        Student student = new Student();
        student.setSex("1");
        student.setAge(28);
        student.setEmail("1289680419@qq.com");
        student.setStudentName("niejun");
        studentMapper.insert(student);
        throw new RuntimeException();
    }

    @Transactional(rollbackFor = RuntimeException.class, noRollbackFor = Exception.class)
    public void insertTest() {
        Student student = new Student();
        student.setSex("1");
        student.setAge(28);
        student.setEmail("1289680419@qq.com");
        student.setStudentName("niejun");
        studentMapper.insert(student);
        throw new NullPointerException();
    }
}
