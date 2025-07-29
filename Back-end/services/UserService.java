package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.User;
import com.example.demo.Repository.UserRepository;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Register user
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    
    public User registerUser(User user) {
        return userRepository.save(user);
    }


    // Authenticate user during login
    public User authenticate(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }


}

