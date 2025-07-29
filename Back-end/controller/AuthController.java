package com.example.demo.Controller;

import com.example.demo.Entity.User;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*") // Allow all origins (for development)
public class AuthController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private UserRepository userRepository;

    // POST: Register a new user
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        // Check if username already exists
        User existingUser = userService.findByUsername(user.getUsername());       if (existingUser != null) {
            return ResponseEntity.badRequest().body("Username already taken");
        }

        // Save user with plain password and USER role
        user.setRole("USER"); // Set role manually
        User savedUser = userService.registerUser(user);

        return ResponseEntity.ok(savedUser);
    }


    // POST: Login existing user
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        User found = userService.authenticate(user.getUsername(), user.getPassword());
        if (found == null) {
            return ResponseEntity.status(401).body("Invalid username or password");
        }
        return ResponseEntity.ok(found);
    }

}

