package com.example.employeemanager1.controller;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@SpringBootApplication
public class DemoController {
    @GetMapping("/hello")
    public String sayHello(Model model) {
        model.addAttribute("theDate", new java.util.Date());
        return "helloworld";
    }
}
