package com.example.ass.dao;


import com.example.ass.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findAllByOrderByName();
    List<User> findByNameContainingIgnoreCase(String name);
}
