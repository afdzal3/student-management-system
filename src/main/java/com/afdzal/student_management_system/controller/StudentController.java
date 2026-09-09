package com.afdzal.student_management_system.controller;

import com.afdzal.student_management_system.model.Student;
import com.afdzal.student_management_system.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // 1. Display list of students
    @GetMapping("/")
    public String viewHomePage(Model model) {
        model.addAttribute("listStudents", studentService.getAllStudents());
        return "index";
    }

    // 2. Show form for creating a new student
    @GetMapping("/students/new")
    public String showNewStudentForm(Model model) {
        model.addAttribute("student", new Student());
        return "student_form";
    }

    // 3. Save student (Handles both Create and Update)
    @PostMapping("/students")
    public String saveStudent(@Valid @ModelAttribute("student") Student student,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "student_form";
        }

        try {
            studentService.saveStudent(student);
            return "redirect:/";
        } catch (IllegalArgumentException e) {
            bindingResult.rejectValue("email", "error.email", e.getMessage());
            return "student_form";
        }
    }

    // 4. Show form for updating an existing student
    @GetMapping("/students/edit/{id}")
    public String showEditStudentForm(@PathVariable Long id, Model model) {
        Student student = studentService.getStudentById(id);
        model.addAttribute("student", student);
        return "student_form";
    }

    // 5. Delete student
    @GetMapping("/students/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentService.deleteStudentById(id);
        return "redirect:/";
    }
}