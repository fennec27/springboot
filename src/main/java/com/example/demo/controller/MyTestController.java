package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class MyTestController {
    @GetMapping(value = "", produces = "application/json")
    public String getString() {
        return "{\"test\":\"Hello world\"}";
    }
}
