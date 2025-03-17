package com.luv2Code.demomvcsecurity.service;

import com.luv2Code.demomvcsecurity.entity.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> findAll();

    Employee findById(int id);

    Employee save(Employee employee);

    void deleteById(int id);
}
