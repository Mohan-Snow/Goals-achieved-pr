package com.goalsachieved.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/") // This means URL's start with / (after Application path)
public class MainController {

    @GetMapping("/")
    public String greeting() {
        return "greeting";
    }
}
