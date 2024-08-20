package com.example.studentinformationsystem.repositories;


import com.example.studentinformationsystem.models.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {
}



