package com.example.foodasm.service;

import com.example.foodasm.dao.FoodRepository;
import com.example.foodasm.entities.Food;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FoodServiceImpl implements FoodService {

    private FoodRepository foodRepository;
    @Autowired
    public FoodServiceImpl(FoodRepository thefoodRepository) {
        foodRepository = thefoodRepository;
    }

    @Override
    public void save(Food thefood) {
        foodRepository.save(thefood);

    }

    @Override
    public List<Food> findAll() {
        return foodRepository.findAllByOrderByName();
    }

    @Override
    public Food findById(int id) {
        Optional<Food> result = foodRepository.findById(id);
        Food theFood = null;
        if (result.isPresent()) {
            theFood = result.get();

        }
        else {
            throw new RuntimeException("Food not find id" + id);
        }
        return theFood;
    }

    @Override
    public void deleteById(int id) {
        foodRepository.deleteById(id);
    }
}
