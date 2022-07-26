package com.projectG2.ProjectMaven1.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //to make this class a controller
public class HomeController {
    //add a request mapping for our root URL
    @RequestMapping("/")  // / will identify which mapping it'll take.
    public String home() {
        return "Das Boot, reporting for duty!";
    }
}
