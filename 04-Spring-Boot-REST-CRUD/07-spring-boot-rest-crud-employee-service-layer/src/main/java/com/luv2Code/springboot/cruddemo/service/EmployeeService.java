package com.luv2Code.springboot.cruddemo.service;

import com.luv2Code.springboot.cruddemo.entity.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> findAll();
}
