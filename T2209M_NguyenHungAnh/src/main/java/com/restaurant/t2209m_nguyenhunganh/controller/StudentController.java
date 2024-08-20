package com.restaurant.t2209m_nguyenhunganh.controller;

import com.restaurant.t2209m_nguyenhunganh.entities.Student;
import com.restaurant.t2209m_nguyenhunganh.entities.StudentScore;
import com.restaurant.t2209m_nguyenhunganh.repository.StudentRepository;
import com.restaurant.t2209m_nguyenhunganh.repository.StudentScoreRepository;
import com.restaurant.t2209m_nguyenhunganh.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@Controller
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private StudentScoreRepository studentScoreRepository;
    @Autowired
    private SubjectRepository subjectRepository;



    @GetMapping
    public String listStudents(Model model) {
        model.addAttribute("students", studentRepository.findAll());
        model.addAttribute("scores", studentScoreRepository.findAll());
        return "students/list";
    }

    @GetMapping("/add")
    public String showAddStudentForm(Model model) {
        model.addAttribute("student", new Student());
        return "students/add";
    }

    @PostMapping("/add")
    public String addStudent(@ModelAttribute Student student) {
        studentRepository.save(student);
        return "redirect:/students";
    }

    @GetMapping("/{id}/edit")
    public String editStudent(@PathVariable Integer id, Model model) {
        Student student = studentRepository.getReferenceById(id);
        model.addAttribute("student", student);
        return "students";
    }

    @PostMapping("/{id}/update")
    public String updateStudent(@PathVariable Integer id, @ModelAttribute Student student) {
        student.setStudentId(id);
        studentRepository.save(student);
        return "redirect:/students";
    }

    @GetMapping("/{id}/delete")
    public String deleteStudent(@PathVariable Integer id) {
        studentRepository.delete(studentRepository.getReferenceById(id));
        return "redirect:/students";
    }

    @GetMapping("/scores/add")
    public String showAddScoreForm(Model model) {
        model.addAttribute("score", new StudentScore());
        model.addAttribute("students", studentRepository.findAll());
        model.addAttribute("subjects", subjectRepository.findAll());
        return "students/add_score";
    }

    @PostMapping("/scores/add")
    public String addScore(@ModelAttribute StudentScore score) {
        studentScoreRepository.save(score);
        return "redirect:/students";
    }
}
