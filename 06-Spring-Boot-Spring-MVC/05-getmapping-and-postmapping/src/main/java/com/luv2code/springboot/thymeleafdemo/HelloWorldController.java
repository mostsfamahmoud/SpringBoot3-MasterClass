package com.luv2code.springboot.thymeleafdemo;

// Import necessary Spring Framework annotations and classes

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * This controller handles requests for displaying and processing a simple "Hello World" form.
 * <p>
 * It uses Spring MVC annotations to map URLs to corresponding methods and return appropriate view names.
 */
@Controller
public class HelloWorldController {

    /**
     * Displays the initial HTML form for user input.
     * <p>
     * Maps the URL "/showForm" to this method. When accessed, it returns the name of the view
     * to be rendered (`helloworld-form.html`).
     *
     * @return The name of the Thymeleaf template (view) to display the form.
     */
    @GetMapping("/showForm")
    public String showForm() {
        return "helloworld-form";
        // Returns the name of the Thymeleaf view (helloworld-form.html) for the initial form display.
    }

    /**
     * Processes the submitted form data.
     * <p>
     * Maps the URL "/processForm" to this method. When accessed, it returns the name of the view
     * to display the processed results (`helloworld.html`).
     *
     * @return The name of the Thymeleaf template (view) to display the result.
     */
    @RequestMapping("/processForm")
    public String processForm() {
        return "helloworld";
        // Returns the name of the Thymeleaf view (helloworld.html) for displaying the results.
    }

    /**
     * Processes the form data and manipulates it before sending it to the view.
     * <p>
     * Maps the URL "/processFormVersionTwo" to this method. Reads user input from the request,
     * transforms the data, and adds a message to the model for dynamic rendering in the view.
     *
     * @param model   The Spring Model object used to pass data to the view.
     * @param request The HttpServletRequest object used to extract form data.
     * @return The name of the Thymeleaf template (view) to display the result.
     */
    @RequestMapping(value = "/processFormVersionTwo", method = RequestMethod.POST)
    public String letsShoutDude(Model model, HttpServletRequest request) {
        // Read the request parameter from the HTML form
        String studentName = request.getParameter("studentName");

        // Convert the data to uppercase
        studentName = studentName.toUpperCase();

        // Create the message to display
        String result = "Yo! " + studentName;

        // Add the message to the model for dynamic rendering in the view
        model.addAttribute("message", result);

        return "helloworld";
        // Returns the name of the Thymeleaf view (helloworld.html) with the processed data.
    }

    /**
     * Processes the form data using `@RequestParam` to directly bind the form parameter to a method argument.
     * <p>
     * Maps the URL "/processFormVersionThree" to this method. It retrieves the form data, manipulates it,
     * and adds a message to the model for dynamic rendering in the view.
     *
     * @param model       The Spring Model object used to pass data to the view.
     * @param studentName The name entered by the user in the form, automatically bound to this parameter by `@RequestParam`.
     * @return The name of the Thymeleaf template (view) to display the result.
     */
    @PostMapping("/processFormVersionThree")
    public String letsShoutDude(
            Model model,
            @RequestParam("studentName") String studentName) {

        // Create the message to display by converting the student name to uppercase
        String result = "Hey! " + studentName.toUpperCase();

        // Add the message to the model for dynamic rendering in the view
        model.addAttribute("message", result);

        return "helloworld";
        // Returns the name of the Thymeleaf view (helloworld.html) with the processed data.
    }
}
