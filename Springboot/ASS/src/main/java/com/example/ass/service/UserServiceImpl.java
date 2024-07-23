package com.example.ass.service;


import com.example.ass.dao.UserRepository;
import com.example.ass.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository theuserRepository) {
        userRepository = theuserRepository;
    }
    @Override
    public List<User> findAll() {
        return userRepository.findAllByOrderByName();
    }

    @Override
    public User findById(int theId) {
        Optional<User> result = userRepository.findById(theId);
        User theUser = null;
        if (result.isPresent()) {
            theUser = result.get();

        }
        else {
            throw new RuntimeException("User not find id" + theId);
        }
        return theUser;
    }

    @Override
    public void save(User theUser) {
    userRepository.save(theUser);
    }

    @Override
    public void deleteById(int theId) {
    userRepository.deleteById(theId);
    }

    @Override
    public List<User> findByName(String name) {
        return userRepository.findByNameContainingIgnoreCase(name);
    }
}
