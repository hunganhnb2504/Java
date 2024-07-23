package com.example.ass.service;
import com.example.ass.entities.User;

import java.util.List;

public interface UserService {
    public List<User> findAll();
    public User findById(int theId);
    public void save(User theUser);
    public void deleteById(int TheId);
    List<User> findByName(String name);
}