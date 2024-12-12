package com.luv2code.springboot.thymeleafdemo;

// Import necessary Spring Framework classes

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * This is a Spring MVC Controller class that handles HTTP requests
 * for the Thymeleaf Demo application.
 */
@Controller
public class DemoController {

    /**
     * Handles GET requests to the "/hello" endpoint.
     * <p>
     * This method populates the model with the current date and time
     * and returns the name of a Thymeleaf template to be rendered.
     *
     * @param model The model object used to pass data to the view.
     * @return The name of the Thymeleaf template ("helloworld") to render.
     */
    @GetMapping("/hello")
    public String sayHello(Model model) {
        // Add the current date to the model with the attribute name "theDate"
        model.addAttribute("theDate", java.time.LocalDate.now());

        // Return the name of the Thymeleaf template to render
        return "helloworld";
    }
}
