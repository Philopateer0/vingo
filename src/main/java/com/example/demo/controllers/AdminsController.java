package com.example.demo.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.example.demo.services.UserRegistrationService;

@RestController
@RequestMapping("/admin")
public class AdminsController {

    private final UserRegistrationService userService;

    // Constructor injection
    public AdminsController(UserRegistrationService userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create-user")
    public ResponseEntity<String> createUser() {
        return ResponseEntity.ok("You are authorized to create a user.");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable("id") int id) {
        try {
            userService.deleteUser(userService.getUserById(id).getUsername());
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
}




