package com.example.demo.controllers;

import com.example.demo.services.UserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/instructor")
@RequiredArgsConstructor
public class instructorsController {
    @Autowired
    private UserRegistrationService userService;

}
