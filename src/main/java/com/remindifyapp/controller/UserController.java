package com.remindifyapp.controller;

import com.remindifyapp.entity.AuthUser;
import com.remindifyapp.repository.AuthUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;
import java.util.Optional;

@RestController
public class UserController {

    private final AuthUserRepository authUserRepository;
    private final PasswordEncoder passwordEncoder;

    public UserController(AuthUserRepository authUserRepository, PasswordEncoder passwordEncoder) {
        this.authUserRepository = authUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public String registerUser(@RequestBody Map<String, String> user) {
        if (user.get("username") == null || user.get("password") == null) {
            return "Username and password must be provided";
        }

        if (authUserRepository.findByUsername(user.get("username")).isPresent()) {
            return "Username already exists";
        }

        AuthUser newUser = AuthUser.builder()
                .username(user.get("username"))
                .password(passwordEncoder.encode(user.get("password")))
                .birthday(new Date())
                .active(true)
                .build();
        authUserRepository.save(newUser);
        return "User registered successfully";
    }

        @GetMapping("/login")
    public String loginUser(@RequestBody Map<String, String> user) {
        if (user.get("username") == null || user.get("password") == null) {
            return "Username and password must be provided";
        }

        Optional<AuthUser> optionalUser = authUserRepository.findByUsername(user.get("username"));
        if (optionalUser.isPresent()) {
            AuthUser authUser = optionalUser.get();
            if (passwordEncoder.matches(user.get("password"), authUser.getPassword())) {
                // You can add session management logic here
                return "Login successful";
            } else {
                return "Invalid password";
            }
        } else {
            return "User not found";
        }
    }
}
