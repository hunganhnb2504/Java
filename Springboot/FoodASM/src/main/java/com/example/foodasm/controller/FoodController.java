package com.example.foodasm.controller;

import com.example.foodasm.entities.Food;
import com.example.foodasm.service.FoodService;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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



//    @PostMapping("/save")
//    public String saveFood(@ModelAttribute("food") Food theFood) {
//        foodService.save(theFood);
//        return "redirect:list";
//    }

    @PostMapping("/delete")
    public String deleteFood(@RequestParam("FoodId") int theFoodId) {
        foodService.deleteById(theFoodId);
        return "redirect:list";
    }

    @PostMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("foodId") int theId, Model model) {
        Food theFood = foodService.findById(theId);
        model.addAttribute("food", theFood);
        return "food/show-add-food";
    }


    private static String UPLOADED_FOLDER = "src/main/resources/static/images"; // Path to static folder

    @PostMapping("/save")
    public String saveFood(@ModelAttribute("food") Food theFood,
                           @RequestParam("file") MultipartFile file,
                           Model model) {

        // Save the uploaded file to the specified folder
        if (!file.isEmpty()) {
            try {
                // Create directory if it doesn't exist
                File directory = new File(UPLOADED_FOLDER);
                if (!directory.exists()) {
                    directory.mkdirs();
                }

                // Save the file
                byte[] bytes = file.getBytes();
                Path path = Paths.get(UPLOADED_FOLDER + File.separator + file.getOriginalFilename());
                Files.write(path, bytes);

                // Set the image path to the Food object
                theFood.setImage("/images/" + file.getOriginalFilename());

            } catch (IOException e) {
                e.printStackTrace();
                model.addAttribute("message", "File upload failed: " + e.getMessage());
                return "error"; // Return to a custom error page
            }
        }

        // Save the Food object
        foodService.save(theFood);

        // Redirect to prevent duplicate submissions
        return "redirect:list";
    }
}
