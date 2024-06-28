package com.remindifyapp.controller;

import com.remindifyapp.bean.ResponseDTO;
import com.remindifyapp.bean.UserDTO;
import com.remindifyapp.entity.AuthUser;
import com.remindifyapp.repository.AuthUserRepository;
import com.remindifyapp.util.ImageUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class UserController {

    private final AuthUserRepository authUserRepository;
    private final PasswordEncoder passwordEncoder;

    private ResponseDTO responseDTO;

    public UserController(AuthUserRepository authUserRepository, PasswordEncoder passwordEncoder) {
        this.authUserRepository = authUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> registerUser(@Valid @RequestBody UserDTO userDTO) {
        if (authUserRepository.findByUsername(userDTO.getUsername()).isPresent()) {
            return new ResponseEntity<>(Map.of("success", "false", "message", "Username already exists"), HttpStatus.BAD_REQUEST);
        }

        if (userDTO.getImage() != null && !userDTO.getImage().isEmpty()) {
            try {
                byte[] imageBytes = ImageUtils.decodeBase64ToImage(userDTO.getImage());
                // Save imageBytes to database or file system
            } catch (IllegalArgumentException e) {
                return new ResponseEntity<>(Map.of("success", "false", "message", "Invalid Base64 string"), HttpStatus.BAD_REQUEST);
            } catch (Exception e) {
                return new ResponseEntity<>(Map.of("success", "false", "message", "Failed to save image"), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        AuthUser newUser = AuthUser.builder()
                .username(userDTO.getUsername())
                .email(userDTO.getEmail())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .birthday(userDTO.getBirthday())
                .image(userDTO.getImage())
                .active(true)
                .build();
        authUserRepository.save(newUser);
        return new ResponseEntity<>(Map.of("success", "true", "message", "User registered successfully"), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseDTO loginUser(@RequestParam String username, @RequestParam String password) {
        responseDTO = new ResponseDTO(); // Initialize responseDTO here

        Optional<AuthUser> optionalUser = authUserRepository.findByUsername(username);
        if (optionalUser.isPresent()) {
            AuthUser authUser = optionalUser.get();
            if (passwordEncoder.matches(password, authUser.getPassword())) {
                responseDTO.setStatusCode(200);
                responseDTO.setMessage("success");
                responseDTO.setData(authUser);
            } else {
                responseDTO.setStatusCode(401);
                responseDTO.setMessage("Invalid password");
                responseDTO.setData(null);
            }
        } else {
            responseDTO.setStatusCode(404);
            responseDTO.setMessage("User not found");
            responseDTO.setData(null);
        }

        return responseDTO;
    }

    @GetMapping("/")
    public String getHello() {
        return "Hello";
    }
}
