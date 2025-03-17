// Define the package where the controller resides
package com.luv2Code.demomvcsecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller class for handling login and access-denied pages.
 * This class defines endpoints for displaying custom login and access-denied views.
 */
@Controller // Marks this class as a Spring MVC Controller
public class LoginController {

    /**
     * Handles GET requests to "/showMyCustomLoginForm".
     * This method returns the login page view.
     *
     * @return The name of the login view template.
     */
    @GetMapping("/showMyCustomLoginForm")
    public String showCustomLoginForm() {
        // Return the custom login page view (fancy-login.html or fancy-login.jsp)
        return "fancy-login";

        // Uncomment the line below to use a simpler login page instead.
        // return "plain-login";
    }

    /**
     * Handles GET requests to "/access-denied".
     * This method returns the access-denied page view when a user is not authorized.
     *
     * @return The name of the access-denied view template.
     */
    @GetMapping("/access-denied")
    public String showAccessDenied() {
        return "access-denied"; // Return the access-denied page view
    }
}
