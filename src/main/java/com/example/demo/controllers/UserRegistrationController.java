package com.example.demo.controllers;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.models.User;
import com.example.demo.models.AuthRequest;
import com.example.demo.services.*;
import com.example.demo.services.UserRegistrationService;

@RestController
@RequestMapping("/auth")
public class UserRegistrationController {

    @Autowired
    private UserRegistrationService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userService.addPerson(user);
            return ResponseEntity.ok("User registered successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthRequest authRequest) {
        try {
            //validate with username&pass
            User user = userService.getUser(authRequest.getUsername());
            if (user != null && passwordEncoder.matches(authRequest.getPassword(), user.getPassword())) {
                //generate token
                String token = jwtService.generateToken(user.getUsername());
                return ResponseEntity.ok(token);
            }
            return ResponseEntity.status(401).body("Invalid credentials");
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }




    @DeleteMapping("/delete/{id}")
    public void deletePerson(@PathVariable("id") int id) {
        userService.deleteUser(String.valueOf(id));
    }

    @GetMapping("/view/{id}")
    public User getPerson(@PathVariable("id") int id) {
        return userService.getUser(String.valueOf(id));
    }

    @GetMapping("/viewall")
    public ResponseEntity<List<User>> getAll() {
        try {
            List<User> users = userService.getAllUsers();
            return ResponseEntity.ok(users);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }
}