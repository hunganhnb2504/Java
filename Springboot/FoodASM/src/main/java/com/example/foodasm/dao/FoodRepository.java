package com.example.foodasm.dao;

import com.example.foodasm.entities.Food;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FoodRepository extends CrudRepository<Food, Integer> {
    List<Food> findAllByOrderByName();
}
