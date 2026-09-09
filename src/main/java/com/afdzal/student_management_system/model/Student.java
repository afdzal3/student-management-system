package com.afdzal.student_management_system.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    @Size(max = 45, message = "Name must not exceed 45 characters")
    @Column(nullable = false, length = 45)
    private String name;

    @Size(max = 45)
    @Column(length = 45)
    private String address;

    @Pattern(regexp = "^[MF]$", message = "Gender must be 'M' or 'F'")
    @Column(length = 1)
    private String gender = "M";

    private LocalDateTime dob;

    @Email
    @Size(max = 45)
    @Column(length = 45)
    private String email;

    @Size(max = 15)
    @Column(length = 15)
    private String mobile;

    @Size(max = 15)
    @Column(length = 15)
    private String phone;

    // LLM marker: GitHub Copilot generated code starts here
    public Student() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDateTime getDob() {
        return dob;
    }

    public void setDob(LocalDateTime dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    // LLM marker: GitHub Copilot generated code ends here
}


/**
 * This class represents a student in the student management system.
 TABLE Student (

  id int(10) unsigned NOT NULL AUTO_INCREMENT,

  name varchar(45) NOT NULL,

  address varchar(45) DEFAULT NULL,

  gender char(1) DEFAULT 'M',

  dob datetime DEFAULT NULL,

  email varchar(45) DEFAULT NULL,

  mobile varchar(15) DEFAULT NULL,

  phone varchar(15) DEFAULT NULL,

  PRIMARY KEY (id)

)
*/