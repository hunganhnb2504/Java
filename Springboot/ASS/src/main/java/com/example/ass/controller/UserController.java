package com.example.ass.controller;


import com.example.ass.entities.User;
import com.example.ass.service.UserService;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
@Controller
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    public UserController(UserService theuserService) {
        userService = theuserService;
    }

    @GetMapping("/list")
    public String listUser(@RequestParam(value = "name", required = false) String name, Model model) {
        List<User> theUsers;
        if (name != null && !name.isEmpty()) {
            theUsers = userService.findByName(name);
        } else {
            theUsers = userService.findAll();
        }
        model.addAttribute("users", theUsers);
        return "user/list-user";
    }
    @GetMapping("/showformadduser")
    public String showFormAddUser( Model model) {
        User theUser = new User();
        model.addAttribute("user", theUser);
        return "user/form-add-user";
    }
    @PostMapping("/updateuser")
    public String updateUser(@RequestParam("UserId") int theId, Model model) {
        User theUser = userService.findById(theId);
        model.addAttribute("user", theUser);
        return "user/form-add-user";
    }
    @PostMapping("/save")
    public String saveUser(@ModelAttribute("user") User theUser) {
        userService.save(theUser);
        return "redirect:list";
    }
    @PostMapping("/delete")
    public String deleteUser(@RequestParam("UserId") int theId) {
        userService.deleteById(theId);
        return "redirect:list";
    }
}
