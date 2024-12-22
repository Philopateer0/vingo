package com.example.demo.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.example.demo.services.UserRegistrationService;


@RestController
@RequestMapping("/admin")
public class AdminsController {

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create-user")
    public String createUser() {
        return "Admin creates a new user.";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable("id") int id) {
        try {
            UserRegistrationService userService = new UserRegistrationService();
            userService.deleteUser(userService.getUserById(id).getUsername());
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
    //should add manage courses


}