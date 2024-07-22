package com.gigantic.entity.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping("/")
    public String viewHomePage() {
        return "Client-API Correct";
    }
}
