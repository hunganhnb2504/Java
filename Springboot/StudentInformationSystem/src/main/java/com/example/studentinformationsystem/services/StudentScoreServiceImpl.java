package com.example.studentinformationsystem.services;

import com.example.studentinformationsystem.models.StudentScore;
import com.example.studentinformationsystem.repositories.StudentScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentScoreServiceImpl implements StudentScoreService {

    @Autowired
    private StudentScoreRepository studentScoreRepository;

    @Override
    public List<StudentScore> getAllStudentScores() {
        return studentScoreRepository.findAll();
    }

    @Override
    public Optional<StudentScore> getStudentScoreById(Integer id) {
        return studentScoreRepository.findById(id);
    }

    @Override
    public StudentScore saveStudentScore(StudentScore studentScore) {
        return studentScoreRepository.save(studentScore);
    }

    @Override
    public void deleteStudentScore(Integer id) {
        studentScoreRepository.deleteById(id);
    }
}
