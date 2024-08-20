package com.example.studentinformationsystem.controller;

import com.example.studentinformationsystem.models.Student;
import com.example.studentinformationsystem.models.Subject;
import com.example.studentinformationsystem.models.StudentScore;
import com.example.studentinformationsystem.services.StudentScoreService;
import com.example.studentinformationsystem.services.StudentService;
import com.example.studentinformationsystem.services.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/scores")
public class StudentScoreController {

    @Autowired
    private StudentScoreService studentScoreService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private SubjectService subjectService;

    @GetMapping
    public String listScores(Model model) {
        model.addAttribute("studentScores", studentScoreService.getAllStudentScores());
        return "student-list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("studentScore", new StudentScore());
        List<Student> students = studentService.getAllStudents();
        List<Subject> subjects = subjectService.getAllSubjects();
        model.addAttribute("students", students);
        model.addAttribute("subjects", subjects);
        return "student_score_form";
    }

    @PostMapping("/add")
    public String addStudentScore(@ModelAttribute StudentScore studentScore) {
        studentScoreService.saveStudentScore(studentScore);
        return "redirect:/scores";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        StudentScore studentScore = studentScoreService.getStudentScoreById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid score Id:" + id));
        model.addAttribute("studentScore", studentScore);
        List<Student> students = studentService.getAllStudents();
        List<Subject> subjects = subjectService.getAllSubjects();
        model.addAttribute("students", students);
        model.addAttribute("subjects", subjects);
        return "student_score_form";
    }

    @PostMapping("/update")
    public String updateStudentScore(@ModelAttribute StudentScore studentScore) {
        studentScoreService.saveStudentScore(studentScore);
        return "redirect:/scores";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudentScore(@PathVariable Integer id) {
        studentScoreService.deleteStudentScore(id);
        return "redirect:/scores";
    }
}
