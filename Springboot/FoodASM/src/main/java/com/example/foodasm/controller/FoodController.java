package com.example.foodasm.controller;

import com.example.foodasm.entities.Food;
import com.example.foodasm.service.FoodService;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
@Controller
@RequestMapping("/food")
public class FoodController {
    private FoodService foodService;
    public FoodController(FoodService thefoodService) {
        foodService = thefoodService;
    }

    @GetMapping("/list")
    public String listFood(Model model) {
        List<Food> foodList = foodService.findAll();
        model.addAttribute("foods", foodList);
        return "food/list-food";
    }

    @GetMapping("/showaddfood")
    public String showFood(Model model) {
        Food theFood = new Food();
        model.addAttribute("food", theFood);
        return "food/show-add-food";
    }

    @PostMapping("/updatefood")
    public String updateUser(@RequestParam("FoodId") int theId, Model model) {
        Food theFood = foodService.findById(theId);
        model.addAttribute("food", theFood);
        return "food/show-add-food";
    }

    @PostMapping("/save")
    public String saveFood(@ModelAttribute("food") Food theFood) {
        foodService.save(theFood);
        return "redirect:list";
    }

    @PostMapping("/delete")
    public String deleteFood(@RequestParam("FoodId") int theFoodId) {
        foodService.deleteById(theFoodId);
        return "redirect:list";
    }
}
