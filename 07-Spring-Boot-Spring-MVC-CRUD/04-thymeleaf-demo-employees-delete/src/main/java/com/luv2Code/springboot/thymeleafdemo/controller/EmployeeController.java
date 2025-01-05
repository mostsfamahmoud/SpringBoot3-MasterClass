// Define the package for the controller
package com.luv2Code.springboot.thymeleafdemo.controller;

import com.luv2Code.springboot.thymeleafdemo.model.Employee;
import com.luv2Code.springboot.thymeleafdemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Controller class for handling Employee-related requests.
 */
@Controller
public class EmployeeController {

    // Injecting the EmployeeService to interact with the service layer
    private EmployeeService employeeService;

    // Constructor-based dependency injection for EmployeeService
    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    /**
     * Redirect the root URL ("/") to the employees list page.
     *
     * @return Redirects to "/employees/list".
     */
    @GetMapping("/")
    public String redirectRoot() {
        return "redirect:/employees/list";
    }

    /**
     * Show the form to add a new employee.
     *
     * @param model The model to pass data to the view.
     * @return The Thymeleaf template "employees/employee-form".
     */
    @GetMapping("/employees/showAddEmployeeForm")
    public String showAddEmployeeForm(Model model) {
        // Pass an empty Employee object to the model for form binding
        model.addAttribute("employee", new Employee());

        // Return the form view
        return "employees/employee-form";
    }

    /**
     * Show the form to update an existing employee.
     * This method uses a POST request to pass the employee ID via a form submission.
     * An alternative approach involves using a hyperlink with button styling.
     * <p>
     * If the first approach (using a hyperlink with button styling) is used for the update functionality:
     * - You need to use a `@GetMapping` instead of `@PostMapping` for this method.
     * - The employee ID would be passed as a query parameter in the URL.
     * <p>
     * Example URL for the first approach:
     * /employees/showUpdateEmployeeForm?employeeId=123
     *
     * @param id    The ID of the employee to update.
     * @param model The model to pass data to the view.
     * @return The Thymeleaf template "employees/employee-form".
     */
    @PostMapping("/employees/showUpdateEmployeeForm")
    //@GetMapping("/employees/showUpdateEmployeeForm")
    public String showUpdateEmployeeForm(@RequestParam("employeeId") int id, Model model) {
        // Retrieve the employee by ID from the service layer
        Employee employee = employeeService.findById(id);

        // Add the employee as a model attribute to pre-populate the form
        model.addAttribute("employee", employee);

        // Return the form view
        return "employees/employee-form";
    }

    /**
     * Delete an employee based on the employee ID.
     * This method uses a POST request to pass the employee ID via a form submission.
     * An alternative approach involves using a hyperlink with button styling.
     * <p>
     * If the first approach (using a hyperlink with button styling) is used for the delete functionality:
     * - You need to use a `@GetMapping` instead of `@PostMapping` for this method.
     * - The employee ID would be passed as a query parameter in the URL.
     * <p>
     * Example URL for the first approach:
     * /employees/delete?employeeId=123
     *
     * @param id The ID of the employee to delete.
     * @return Redirects to the employee list page after deletion.
     */
    @PostMapping("/employees/delete")
    //@GetMapping("/employees/delete")
    public String deleteEmployee(@RequestParam("employeeId") int id) {
        // Delete the employee by ID from the service layer
        employeeService.deleteById(id);

        // Redirect to the employees list page
        return "redirect:/employees/list";
    }


    /**
     * Display the list of employees.
     *
     * @param model The model to pass data to the view.
     * @return The Thymeleaf template "employees/list-employees".
     */
    @GetMapping("/employees/list")
    public String listEmployees(Model model) {
        // Retrieve all employees from the service layer
        List<Employee> employees = employeeService.findAll();

        // Add the employees list to the model
        model.addAttribute("employees", employees);

        // Return the list view
        return "employees/list-employees";
    }

    /**
     * Save or update an employee.
     *
     * @param employee The employee object to save.
     * @return Redirects to the employees list page.
     */
    @PostMapping("/employees/save")
    public String saveEmployee(@ModelAttribute("employee") Employee employee) {
        // Save the employee using the service layer
        employeeService.save(employee);

        // Redirect to the employees list page to prevent duplicate submissions
        // Follows the Post/Redirect/Get pattern
        return "redirect:/employees/list";
    }
}
