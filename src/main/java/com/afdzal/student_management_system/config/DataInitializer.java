package com.afdzal.student_management_system.config;

import com.afdzal.student_management_system.model.Student;
import com.afdzal.student_management_system.repo.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.LocalDateTime;

@Configuration
public class DataInitializer {

    @Bean
    @Profile({"dev", "local"})
    CommandLineRunner initStudents(StudentRepository studentRepository) {
        return args -> {
            if (studentRepository.count() == 0) {
                Student student1 = new Student();
                student1.setName("Alice Johnson");
                student1.setAddress("New York");
                student1.setGender("F");
                student1.setDob(LocalDateTime.of(2001, 5, 12, 0, 0));
                student1.setEmail("alice@example.com");
                student1.setMobile("1234567890");
                student1.setPhone("9876543210");

                Student student2 = new Student();
                student2.setName("Bob Smith");
                student2.setAddress("London");
                student2.setGender("M");
                student2.setDob(LocalDateTime.of(1998, 9, 24, 0, 0));
                student2.setEmail("bob@example.com");
                student2.setMobile("1112223333");
                student2.setPhone("4445556666");

                Student student3 = new Student();
                student3.setName("Charlie Brown");
                student3.setAddress("Sydney");
                student3.setGender("M");
                student3.setDob(LocalDateTime.of(2003, 1, 8, 0, 0));
                student3.setEmail("charlie@example.com");
                student3.setMobile("2223334444");
                student3.setPhone("5556667777");

                studentRepository.save(student1);
                studentRepository.save(student2);
                studentRepository.save(student3);
            }
        };
    }
}
