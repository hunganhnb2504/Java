package com.example.employeemanager1.service;

import com.example.employeemanager1.dao.EmployeeRepository;
import com.example.employeemanager1.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.employeemanager1.dao.EmployeeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService
{
    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository theEmployeeRepository){
        employeeRepository=theEmployeeRepository;}

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAllByOrderByLastNameAsc();
    }

    @Override
    public Employee findById(int theId)
    {
        Optional<Employee> result = employeeRepository.findById(theId);
        Employee theEmployee =null;
        if (result.isPresent()){
            theEmployee =result.get();
        }
        else {
            throw new RuntimeException("Did not find employee id - " + theId);
        }
        return theEmployee;
    }
    @Override
    public void save(Employee theEmployee) {

    }

    @Override
    public void deleteById(int theId) {

    }
}
