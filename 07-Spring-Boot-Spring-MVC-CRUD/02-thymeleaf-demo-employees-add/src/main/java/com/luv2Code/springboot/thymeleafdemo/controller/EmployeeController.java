package com.luv2Code.springboot.thymeleafdemo.controller;

import com.luv2Code.springboot.thymeleafdemo.model.Employee;
import com.luv2Code.springboot.thymeleafdemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class EmployeeController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/")
    public String redirectRoot() {

        return "redirect:/employees/list";
    }

    @GetMapping("/employees/showAddEmployeeForm")
    public String showAddEmployeeForm(Model model) {
        // Pass the employee object (model attribute) to the model
        model.addAttribute("employee", new Employee());

        // Display this thymeleaf page
        return "employees/employee-form";
    }

    @GetMapping("/employees/list")
    public String listEmployees(Model model) {

        // Get all employees from the DB
        List<Employee> employees = employeeService.findAll();

        // Add this list to the Model
        model.addAttribute("employees", employees);

        // Display this thymeleaf page
        return "employees/list-employees";
    }

    @PostMapping("/employees/save")
    public String saveEmployee(@ModelAttribute("employee") Employee employee) {

        // Save the employee
        employeeService.save(employee);

        // Use redirect to prevent duplicate submissions
        // Post/Redirect/Get Pattern
        return "redirect:/employees/list";
    }
}
