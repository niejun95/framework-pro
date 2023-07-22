package org.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.example.entities.Student;

import java.util.List;

public interface StudentMapper extends BaseMapper<Student> {

    @Select("select * from student where age = ${age}")
    List<Student> queryStudentAgeBigThanParam(@Param("age") Integer age);
}
