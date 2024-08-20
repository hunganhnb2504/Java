package com.restaurant.t2209m_nguyenhunganh.controller;

import com.restaurant.t2209m_nguyenhunganh.entities.Student;
import com.restaurant.t2209m_nguyenhunganh.entities.StudentScore;
import com.restaurant.t2209m_nguyenhunganh.entities.Subject;
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
        return "students/add_score"; // Tên của template HTML
    }

    @PostMapping("/scores/add")
    public String addScore(@RequestParam int studentId,
                           @RequestParam int subjectId,
                           @RequestParam double score1,
                           @RequestParam double score2) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid student Id:" + studentId));
        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid subject Id:" + subjectId));
        StudentScore score = new StudentScore();
        score.setStudent(student);
        score.setSubject(subject);
        score.setScore1(score1);
        score.setScore2(score2);
        studentScoreRepository.save(score);
        return "redirect:/students";
    }

    // Hiển thị biểu mẫu thêm môn học
    @GetMapping("/subjects/add")
    public String showAddSubjectForm(Model model) {
        model.addAttribute("subject", new Subject());
        return "add_subject";
    }

    // Xử lý việc thêm môn học
    @PostMapping("/subjects/add")
    public String addSubject(@ModelAttribute Subject subject) {
        subjectRepository.save(subject);
        return "redirect:/students/subjects";
    }

    // Hiển thị danh sách môn học
    @GetMapping("/subjects")
    public String listSubjects(Model model) {
        model.addAttribute("subjects", subjectRepository.findAll());
        return "list_subjects";
    }

    // Hiển thị biểu mẫu gán môn học cho sinh viên
    @GetMapping("/assign/subject")
    public String showAssignSubjectForm(Model model) {
        model.addAttribute("students", studentRepository.findAll());
        model.addAttribute("subjects", subjectRepository.findAll());
        return "assign_subject";
    }

    // Xử lý việc gán môn học cho sinh viên
//    @PostMapping("/assign/subject")
//    public String assignSubjectToStudent(@RequestParam int studentId, @RequestParam int subjectId) {
//        Student student = studentRepository.findById(studentId).orElseThrow(() -> new IllegalArgumentException("Invalid student ID"));
//        Subject subject = subjectRepository.findById(subjectId).orElseThrow(() -> new IllegalArgumentException("Invalid subject ID"));
//
//        student.getSubjects().add(subject);
//        studentRepository.save(student);
//
//        return "redirect:/students";
//    }
}
