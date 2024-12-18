package com.example.demo.controllers;

import java.util.List;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.models.User;
import com.example.demo.services.UserRegistrationService;
import com.example.demo.models.AuthRequest;
import com.example.demo.models.User;
import com.example.demo.services.JwtService;
import com.example.demo.services.UserRegistrationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping
@RequiredArgsConstructor

public class UserRegistrationController {
    private final PasswordEncoder passwordEncoder;
    
 @Autowired
private UserRegistrationService userService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthRequest authRequest) {
        User user = userService.getUser(authRequest.getUsername());
        if (user != null && passwordEncoder.matches(authRequest.getPassword(), user.getPassword())) {
            JwtService jwtService = null;

            String token = jwtService.generateToken(user.getUsername());
            return ResponseEntity.ok(token);
        }
        return ResponseEntity.status(401).body("Invalid credentials");
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.addPerson(user);
        return ResponseEntity.ok("User registered successfully");
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
	public List<User> getAll() {
		return userService.getAllUsers();
	}


}
