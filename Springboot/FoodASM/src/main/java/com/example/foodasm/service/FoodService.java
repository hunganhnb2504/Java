package com.example.foodasm.service;

import com.example.foodasm.entities.Food;

import java.util.List;

public interface FoodService {
    public void save(Food thefood);
    public List<Food> findAll();
    public Food findById(int TheId);
    public void deleteById(int TheId);

}
