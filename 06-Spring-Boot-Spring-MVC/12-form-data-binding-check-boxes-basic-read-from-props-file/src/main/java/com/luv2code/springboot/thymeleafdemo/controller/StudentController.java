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
 * This class demonstrates the use of Spring MVC to:
 * 1. Serve a form for user input.
 * 2. Process form submissions and bind data to a model object.
 * 3. Display a confirmation page with the submitted data.
 */
@Controller
public class StudentController {

    /**
     * Inject the "countries" property from the application.properties file.
     * This property contains a comma-separated list of country names.
     * Example in application.properties:
     * countries=USA,India,Germany,France
     */
    @Value("${countries}")
    private List<String> countries;

    /**
     * Inject the "languages" property from the application.properties file.
     * This property contains a comma-separated list of programming languages.
     * Example in application.properties:
     * languages=Java,Python,C++,JavaScript
     */
    @Value("${languages}")
    private List<String> languages;

    /**
     * Inject the "operatingSystems" property from the application.properties file.
     * This property contains a list of operating systems for checkboxes.
     * Example in application.properties:
     * operatingSystems=Microsoft Windows,MacOS,Linux
     */
    @Value("${operatingSystems}")
    private List<String> operatingSystems;

    /**
     * Handles GET requests for displaying the student form.
     * <p>
     * This method prepares the model with:
     * 1. An empty Student object to bind the form fields.
     * 2. A list of countries, programming languages, and operating systems.
     *
     * @param model A Model object used to pass data between the controller and the view.
     * @return The name of the Thymeleaf template ("student-form") that renders the form.
     */
    @GetMapping("/showStudentForm")
    public String showForm(Model model) {
        // Create a new Student object and add it to the model.
        // This object will be used to hold form data.
        model.addAttribute("student", new Student());

        // Add the list of countries to the model to populate the dropdown menu.
        model.addAttribute("countries", countries);

        // Add the list of languages to the model for radio button options.
        model.addAttribute("languages", languages);

        // Add the list of operating systems to the model for checkbox options.
        model.addAttribute("operatingSystems", operatingSystems);

        // Return the name of the Thymeleaf template for the student form.
        return "student-form";
    }

    /**
     * Handles POST requests for processing the submitted student form.
     *
     * @param theStudent The Student object populated with form data.
     * @return The name of the Thymeleaf template ("student-confirmation") to display the confirmation page.
     * @ModelAttribute binds form data to the "student" model object automatically.
     * This object contains all the fields submitted by the user.
     */
    @PostMapping("/processStudentForm")
    public String processForm(@ModelAttribute("student") Student theStudent) {
        // Log the student data to the console (useful for debugging and verification).
        System.out.println(theStudent.toString());

        // Return the name of the Thymeleaf template for displaying the confirmation page.
        return "student-confirmation";
    }
}
