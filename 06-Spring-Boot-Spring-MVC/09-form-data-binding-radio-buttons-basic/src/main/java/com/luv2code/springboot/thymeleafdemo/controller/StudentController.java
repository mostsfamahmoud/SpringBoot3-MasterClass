// Define the package for the controller
package com.luv2code.springboot.thymeleafdemo.controller;

// Import necessary classes for Spring MVC and other features
import com.luv2code.springboot.thymeleafdemo.model.Student;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * Controller class for handling student-related web requests.
 * <p>
 * This class demonstrates the use of Spring MVC for handling form submission
 * and binding form data to a model object.
 */
@Controller
public class StudentController {

    // Inject the "countries" property from the application.properties file
    // The list will be populated based on the comma-separated values in the properties file
    @Value("${countries}")
    private List<String> countries;

    /**
     * Handles GET requests for displaying the student form.
     *
     * @param model A Model object used to pass data between the controller and the view.
     * @return The name of the Thymeleaf template to render the form.
     */
    @GetMapping("/showStudentForm")
    public String showForm(Model model) {
        // Create a new Student object and add it to the model
        // This object will be used to bind form data
        model.addAttribute("student", new Student());

        // Add the list of countries (from properties file) to the model
        // This list will populate a dropdown menu in the form
        model.addAttribute("countries", countries);

        // Return the name of the Thymeleaf template to render the student form
        return "student-form";
    }

    /**
     * Handles POST requests for processing the submitted student form.
     * <p>
     * The form data is bound to the "student" model attribute using @ModelAttribute.
     *
     * @param theStudent The Student object containing the submitted form data.
     * @return The name of the Thymeleaf template to display the confirmation page.
     */
    @PostMapping("/processStudentForm")
    public String processForm(@ModelAttribute("student") Student theStudent) {

        // Log the student data to the console (optional, for debugging purposes)
        System.out.println(theStudent.toString());

        // Return the name of the Thymeleaf template for displaying the confirmation page
        return "student-confirmation";
    }
}
