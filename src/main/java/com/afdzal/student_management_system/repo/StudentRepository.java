package com.afdzal.student_management_system.repo;

import com.afdzal.student_management_system.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    // LLM marker: GitHub Copilot generated repository methods start here
    Optional<Student> findByEmail(String email);

    Optional<Student> findByMobile(String mobile);

    Optional<Student> findByPhone(String phone);

    List<Student> findByNameContainingIgnoreCase(String name);

    List<Student> findByGender(String gender);

    boolean existsByEmail(String email);

    boolean existsByMobile(String mobile);
    // LLM marker: GitHub Copilot generated repository methods end here


}
