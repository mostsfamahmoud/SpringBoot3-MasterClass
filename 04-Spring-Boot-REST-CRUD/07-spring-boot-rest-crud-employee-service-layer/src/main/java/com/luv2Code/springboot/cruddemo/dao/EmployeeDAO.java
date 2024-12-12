package com.luv2Code.springboot.cruddemo.dao;

import com.luv2Code.springboot.cruddemo.entity.Employee;

import java.util.List;

public interface EmployeeDAO {

    List<Employee> findAll();
}
