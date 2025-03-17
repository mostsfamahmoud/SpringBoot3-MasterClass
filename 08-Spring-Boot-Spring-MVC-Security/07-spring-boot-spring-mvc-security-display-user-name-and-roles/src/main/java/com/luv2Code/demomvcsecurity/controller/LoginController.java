package com.luv2Code.demomvcsecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/showMyCustomLoginForm")
    public String showCustomLoginForm() {
        //return "plain-login";
        return "fancy-login";
    }

}
