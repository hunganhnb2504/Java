package com.restaurant.t2209m_nguyenhoangphuong.repository;

import com.restaurant.t2209m_nguyenhoangphuong.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}
