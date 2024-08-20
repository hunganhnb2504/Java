package com.example.studentinformationsystem.services;


import com.example.studentinformationsystem.models.Subject;

import java.util.List;
import java.util.Optional;

public interface SubjectService {
    List<Subject> getAllSubjects();
    Optional<Subject> getSubjectById(Integer id);
    Subject saveSubject(Subject subject);
    void deleteSubject(Integer id);
}
