package com.restaurant.t2209m_nguyenhunganh.controller;


import com.restaurant.t2209m_nguyenhunganh.entities.Student;
import com.restaurant.t2209m_nguyenhunganh.entities.StudentScore;
import com.restaurant.t2209m_nguyenhunganh.entities.Subject;
import com.restaurant.t2209m_nguyenhunganh.repository.StudentRepository;
import com.restaurant.t2209m_nguyenhunganh.repository.StudentScoreRepository;
import com.restaurant.t2209m_nguyenhunganh.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class StudentScoreController {

    @Autowired
    private StudentScoreRepository studentScoreRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @GetMapping("/scores/new")
    public String showAddScoreForm(Model model) {
        model.addAttribute("studentScore", new StudentScore());
        model.addAttribute("students", studentRepository.findAll());
        model.addAttribute("subjects", subjectRepository.findAll());
        return "add_score";
    }

    @PostMapping("/scores")
    public String addScore(@ModelAttribute("studentScore") StudentScore studentScore) {
        Student student = studentRepository.findById(studentScore.getStudent().getStudentId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid student ID: " + studentScore.getStudent().getStudentId()));
        Subject subject = subjectRepository.findById(studentScore.getSubject().getSubjectId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid subject ID: " + studentScore.getSubject().getSubjectId()));

        studentScore.setStudent(student);
        studentScore.setSubject(subject);

        studentScoreRepository.save(studentScore);
        return "redirect:/"; // Chuyển hướng về danh sách sinh viên
    }
}
