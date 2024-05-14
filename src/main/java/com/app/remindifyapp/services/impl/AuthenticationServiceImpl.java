/**
 * Author : rasintha_j
 * Date : 5/14/2024
 * Time : 2:43 PM
 * Project Name : remindifyapp
 */

package com.app.remindifyapp.services.impl;

import com.app.remindifyapp.dto.JwtAuthenticationResponse;
import com.app.remindifyapp.dto.SignInRequest;
import com.app.remindifyapp.dto.SignUpRequest;
import com.app.remindifyapp.entities.User;
import com.app.remindifyapp.repository.UserRepo;
import com.app.remindifyapp.services.AuthenticationService;
import com.app.remindifyapp.services.JWTService;
import com.app.remindifyapp.util.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;

    public User signup(SignUpRequest signUpRequest) {
        User user = new User();

        user.setEmail(signUpRequest.getEmail());
        user.setFirstName(signUpRequest.getFirstName());
        user.setSecondName(signUpRequest.getLastName());
        user.setRole(Role.user);
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));

        return userRepo.save(user);
    }

    public JwtAuthenticationResponse signin(SignInRequest signInRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getEmail(),
                signInRequest.getPassword()));

        var user = userRepo.findByEmail(signInRequest.getEmail()).orElseThrow(() -> new RuntimeException("Invalid email or password."));
        var jwt = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);

        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
        jwtAuthenticationResponse.setToken(jwt);
        jwtAuthenticationResponse.setRefreshToken(refreshToken);

        return jwtAuthenticationResponse;
    }
}
