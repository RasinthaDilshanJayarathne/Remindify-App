/**
 * Author : rasintha_j
 * Date : 5/14/2024
 * Time : 2:43 PM
 * Project Name : remindifyapp
 */

package com.app.remindifyapp.services.impl;

import com.app.remindifyapp.dto.SignUpRequest;
import com.app.remindifyapp.entities.User;
import com.app.remindifyapp.repository.UserRepo;
import com.app.remindifyapp.services.AuthenticationService;
import com.app.remindifyapp.util.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    public User signup(SignUpRequest signUpRequest) {
        User user = new User();

        user.setEmail(signUpRequest.getEmail());
        user.setFirstName(signUpRequest.getFirstName());
        user.setSecondName(signUpRequest.getLastName());
        user.setRole(Role.user);
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));

        return userRepo.save(user);
    }
}
