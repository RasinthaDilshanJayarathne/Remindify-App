/**
 * Author : rasintha_j
 * Date : 5/14/2024
 * Time : 12:13 PM
 * Project Name : remindifyapp
 */

package com.app.remindifyapp.services;

import org.springframework.security.core.userdetails.UserDetails;

public interface JWTService {

    String extractUserName(String token);

    String generateToken(UserDetails userDetails);

    public boolean isTokenValid(String token, UserDetails userDetails);
}
