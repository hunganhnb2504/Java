package com.restaurant.t2209m_nguyenhunganh.controller;

import com.restaurant.t2209m_nguyenhunganh.entities.Subject;
import com.restaurant.t2209m_nguyenhunganh.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/subjects")
public class SubjectController {

    @Autowired
    private SubjectRepository subjectRepository;

    @GetMapping("/add")
    public String showAddSubjectForm(Model model) {
        model.addAttribute("subject", new Subject());
        return "students/add_subject";
    }

    @PostMapping("/add")
    public String addSubject(@RequestParam String subjectCode, @RequestParam String subjectName, @RequestParam int credit) {
        Subject subject = new Subject();
        subject.setSubjectCode(subjectCode);
        subject.setSubjectName(subjectName);
        subject.setCredit(credit);
        subjectRepository.save(subject);
        return "redirect:/subjects/list";
    }
    @GetMapping("/list")
    public String listSubjects(Model model) {
        model.addAttribute("subjects", subjectRepository.findAll());
        return "students/list_subject"; // Name of the HTML template for listing subjects
    }

}
