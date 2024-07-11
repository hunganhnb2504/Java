package com.example.testspringmvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

@SpringBootApplication
@Controller
public class TestspringmvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestspringmvcApplication.class, args);
    }

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name = "name", required = false,defaultValue = "World") String name, Model model) {
        model.addAttribute("name",name);
        return "greeting";
    }
}
