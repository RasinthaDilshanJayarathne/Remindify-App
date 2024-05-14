/**
 * Author : rasintha_j
 * Date : 5/14/2024
 * Time : 2:36 PM
 * Project Name : remindifyapp
 */

package com.app.remindifyapp.controller;

import com.app.remindifyapp.dto.SignUpRequest;
import com.app.remindifyapp.entities.User;
import com.app.remindifyapp.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<User> signup(@RequestBody SignUpRequest signUpRequest) {
        return ResponseEntity.ok(authenticationService.signup(signUpRequest));
    }
}
