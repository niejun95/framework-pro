package org.example.controller.gerneral;

import org.example.entities.Student;
import org.example.service.StudentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/all")
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @PostMapping("/add")
    public void add() {
        Student student = new Student();
        student.setStudentName("lucy");
        student.setSex("0");
        student.setEmail("lucy@gmail.com");
        student.setAge(18);
        studentService.addStudent(student);
    }
}
