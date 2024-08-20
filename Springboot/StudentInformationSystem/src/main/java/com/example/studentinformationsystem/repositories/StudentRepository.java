package com.example.studentinformationsystem.repositories;

import com.example.studentinformationsystem.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}
