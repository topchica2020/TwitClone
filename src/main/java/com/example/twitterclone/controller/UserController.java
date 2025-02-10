package com.example.twitterclone.controller;

import com.example.twitterclone.model.User;
import com.example.twitterclone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User  registerUser (@RequestBody User user) {
        return userService.saveUser (user);
    }

    @GetMapping("/{username}")
    public User getUser (@PathVariable String username) {
        return userService.findByUsername(username).orElse(null);
    }
}