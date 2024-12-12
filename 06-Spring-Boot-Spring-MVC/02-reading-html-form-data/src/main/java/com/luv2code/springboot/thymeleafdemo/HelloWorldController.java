package com.luv2code.springboot.thymeleafdemo;

// Import necessary Spring Framework annotations and classes
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
    @RequestMapping("/showForm")
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
}
