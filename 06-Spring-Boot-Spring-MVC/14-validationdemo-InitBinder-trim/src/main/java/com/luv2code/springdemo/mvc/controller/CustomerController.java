package com.luv2code.springdemo.mvc.controller;

import com.luv2code.springdemo.mvc.model.Customer;
import jakarta.validation.Valid;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Controller class for handling customer-related requests.
 * <p>
 * This class manages the flow of data between the view (HTML templates) and
 * the model (Customer object) using Spring MVC. It also handles form
 * validation and error handling.
 */
@Controller
public class CustomerController {

    /**
     * Customizes the data binding process for form submissions.
     * <p>
     * This method is invoked by Spring before the binding process begins. It modifies
     * how data from the form is processed and bound to the model attributes.
     *
     * @param dataBinder The WebDataBinder object used for data binding and validation.
     */

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        // Create a StringTrimmerEditor to trim leading and trailing whitespace from string inputs.
        // If the trimmed string is empty, it will be set to null.
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

        // Register the custom editor for all String class fields. This ensures
        // that all string inputs from the form are automatically trimmed and nullified if empty.
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }



    /**
     * Displays the customer registration form.
     * <p>
     * This method initializes the "customer" model attribute and
     * binds it to the form in the view.
     *
     * @param model The Model object used to pass data between the controller and the view.
     * @return The name of the Thymeleaf template for the customer form.
     */
    @GetMapping("/showCustomerForm")
    public String showForm(Model model) {

        // Add a new Customer object to the model.
        // This object will be used to bind form data.
        model.addAttribute("customer", new Customer());

        // Return the view name for the customer registration form.
        return "customer-form";
    }

    /**
     * Processes the submitted customer registration form.
     * <p>
     * This method validates the form data using the @Valid annotation
     * and handles any validation errors. If validation passes, the customer
     * confirmation view is displayed; otherwise, the form is redisplayed
     * with error messages.
     *
     * @param customer      The Customer object containing the submitted form data.
     * @param bindingResult The BindingResult object that holds validation results.
     * @return The name of the Thymeleaf template to render (either the form or confirmation page).
     */
    @PostMapping("/processCustomerForm")
    public String processForm(
            @Valid @ModelAttribute("customer") Customer customer,
            BindingResult bindingResult) {

        // Debugging statement for lastName
        // Using vertical bars to see where whitespaces exist
        System.out.println("Last Name: |" + customer.getLastName() + "|");

        // Check for validation errors.
        if (bindingResult.hasErrors()) {
            // If errors are present, redisplay the form with error messages.
            return "customer-form";
        }

        // If validation passes, display the customer confirmation page.
        return "customer-confirmation";
    }

}
