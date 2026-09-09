package com.afdzal.student_management_system.controller;

import com.afdzal.student_management_system.model.Student;
import com.afdzal.student_management_system.service.AiSearchService;
import com.afdzal.student_management_system.service.StudentService;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class StudentController {

    private final StudentService studentService;
    private final AiSearchService aiSearchService;

    public StudentController(StudentService studentService, AiSearchService aiSearchService) {
        this.studentService = studentService;
        this.aiSearchService = aiSearchService;
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

    // 6. AI-powered search endpoint
    @GetMapping("/students/search")
    public String searchStudents(@RequestParam("query") String query, Model model) {
        List<Student> searchResults = aiSearchService.searchStudentsWithAi(query);
        model.addAttribute("listStudents", searchResults);
        model.addAttribute("searchQuery", query);
        return "index";
    }
}