package com.example.foodasm.dao;

import com.example.foodasm.entities.Food;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoodRepository extends JpaRepository<Food, Integer> {
    List<Food> findAllByOrderByName();
}
