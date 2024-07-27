package com.example.foodasm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller

public class ContentController {
    @GetMapping("/login")
    public String handleLogin() {
        return "food/custom_login";
    }
}
