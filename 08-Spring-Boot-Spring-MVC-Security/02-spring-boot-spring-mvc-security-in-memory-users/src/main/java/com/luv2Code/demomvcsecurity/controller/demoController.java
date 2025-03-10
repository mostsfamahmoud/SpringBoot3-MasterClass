package com.luv2Code.demomvcsecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class demoController {


    @GetMapping("/")
    public String showHome() {

        return "home";
    }
}
