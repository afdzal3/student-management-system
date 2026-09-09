package com.afdzal.student_management_system.service;

import com.afdzal.student_management_system.model.Student;
import java.util.List;

public interface StudentService {
    List<Student> getAllStudents();
    Student getStudentById(Long id);
    Student saveStudent(Student student);
    void deleteStudentById(Long id);
}