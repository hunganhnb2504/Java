package com.restaurant.t2209m_nguyenhoangphuong.repository;

import com.restaurant.t2209m_nguyenhoangphuong.entities.StudentScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentScoreRepository extends JpaRepository<StudentScore, Integer> {
}

