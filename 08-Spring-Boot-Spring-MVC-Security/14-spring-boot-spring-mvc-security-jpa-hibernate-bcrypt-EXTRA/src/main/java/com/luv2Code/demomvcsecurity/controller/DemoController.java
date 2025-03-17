// Define the package where the controller resides
package com.luv2Code.demomvcsecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller class for handling requests to different pages in the application.
 * It defines endpoints for the home page, leaders page, and systems page.
 */
@Controller // Marks this class as a Spring MVC Controller
public class DemoController {

    /**
     * Handles GET requests to the root URL ("/").
     * This method returns the home page view.
     *
     * @return The name of the home view template.
     */
    @GetMapping("/")
    public String showHome() {
        return "home"; // Return the home page view
    }

    /**
     * Handles GET requests to "/leaders".
     * This method returns the leaders page view.
     * Access to this page may be restricted based on security configurations.
     *
     * @return The name of the leaders view template.
     */
    @GetMapping("/leaders")
    public String showLeaders() {
        return "leaders"; // Return the leaders page view
    }

    /**
     * Handles GET requests to "/systems".
     * This method returns the systems page view.
     * Access to this page may be restricted based on security configurations.
     *
     * @return The name of the systems view template.
     */
    @GetMapping("/systems")
    public String showSystem() {
        return "systems"; // Return the systems page view
    }
}
