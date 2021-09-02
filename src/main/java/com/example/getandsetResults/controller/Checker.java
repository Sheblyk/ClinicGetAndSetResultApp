package com.example.getandsetResults.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Checker {

    @GetMapping("/")
    public String sayHello(){
        return "Hello";
    }
}
