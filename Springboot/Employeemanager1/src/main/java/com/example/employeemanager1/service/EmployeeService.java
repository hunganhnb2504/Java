package com.example.employeemanager1.service;

import com.example.employeemanager1.entities.Employee;

import java.util.List;

public interface EmployeeService {
    public List<Employee> findAll() ;

    public Employee findById(int theId);

    public void save(Employee theEmployee);

    public void deleteById(int theId);

}
