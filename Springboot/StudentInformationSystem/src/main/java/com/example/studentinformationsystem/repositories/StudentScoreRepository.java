package com.example.studentinformationsystem.repositories;

import com.example.studentinformationsystem.models.StudentScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentScoreRepository extends JpaRepository<StudentScore, Integer> {


}

