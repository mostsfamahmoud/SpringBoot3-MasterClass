package com.luv2Code.springboot.thymeleafdemo.service;

import com.luv2Code.springboot.thymeleafdemo.model.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> findAll();

    Employee findById(int id);

    Employee save(Employee employee);

    void deleteById(int id);
}
