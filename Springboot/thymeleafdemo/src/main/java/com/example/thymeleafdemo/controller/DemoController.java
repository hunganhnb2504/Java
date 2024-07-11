package com.example.thymeleafdemo.controller;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;

@Controller
@SpringBootApplication

public class DemoController {
    @GetMapping("/hello")
    public String sayhHello(Model theModel) {
        theModel.addAttribute("theDate",new Date());
        return "helloworld";
    }
}
