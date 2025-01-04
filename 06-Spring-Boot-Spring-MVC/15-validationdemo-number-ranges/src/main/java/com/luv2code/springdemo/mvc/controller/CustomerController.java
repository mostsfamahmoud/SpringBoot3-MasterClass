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
     * This method is executed by Spring prior to binding request parameters to the model object.
     * It modifies how input data is processed, ensuring that string fields are trimmed of leading
     * and trailing whitespace, and empty strings are converted to {@code null}.
     *
     * @param dataBinder The {@link WebDataBinder} object used to manage data binding and validation.
     */
    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        // Create a StringTrimmerEditor to handle string trimming.
        // Trims whitespace from the beginning and end of strings.
        // If a string is empty after trimming, it is converted to null.
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

        // Register the StringTrimmerEditor for all String fields.
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    /**
     * Displays the customer registration form.
     * <p>
     * This method prepares the model with a new {@link Customer} object, which is
     * used to bind the form fields in the view. It also specifies the view to be displayed.
     *
     * @param model The {@link Model} object that carries data between the controller and the view.
     * @return The name of the Thymeleaf template for rendering the customer registration form.
     */
    @GetMapping("/showCustomerForm")
    public String showForm(Model model) {

        // Add a new Customer object to the model for form binding.
        model.addAttribute("customer", new Customer());

        // Return the name of the view template for the customer registration form.
        return "customer-form";
    }


    /**
     * Processes the submitted customer registration form.
     * <p>
     * This method handles the form submission by validating the input data provided by the user.
     * It uses the {@code @Valid} annotation to trigger validation rules defined in the {@link Customer} model.
     * The validation results are captured in the {@link BindingResult} object.
     * <p>
     * If validation errors are detected, the form is redisplayed with appropriate error messages.
     * If validation succeeds, the user is redirected to a confirmation page.
     *
     * @param customer      The {@link Customer} object populated with the submitted form data.
     * @param bindingResult The {@link BindingResult} object that holds validation results, including errors if any.
     * @return A string representing the name of the Thymeleaf template to render:
     * - "customer-form" if validation errors occur.
     * - "customer-confirmation" if validation succeeds.
     */
    @PostMapping("/processCustomerForm")
    public String processForm(
            @Valid @ModelAttribute("customer") Customer customer,
            BindingResult bindingResult) {

        // For debugging: Output the submitted customer data to the console.
        System.out.println(customer.toString());

        // Check for validation errors.
        if (bindingResult.hasErrors()) {
            // If errors are present, redisplay the form with error messages.
            return "customer-form";
        }

        // If validation passes, display the customer confirmation page.
        return "customer-confirmation";
    }


}
