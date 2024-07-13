package com.example.usermanager.dao;

import com.example.usermanager.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findAllByOrderByFullName();
}
