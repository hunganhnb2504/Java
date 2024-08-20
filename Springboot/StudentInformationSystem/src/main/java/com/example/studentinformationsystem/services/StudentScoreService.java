package com.example.studentinformationsystem.services;

import com.example.studentinformationsystem.models.StudentScore;

import java.util.List;
import java.util.Optional;

public interface StudentScoreService {
    List<StudentScore> getAllStudentScores();
    Optional<StudentScore> getStudentScoreById(Integer id);
    StudentScore saveStudentScore(StudentScore studentScore);
    void deleteStudentScore(Integer id);
}
