/**
 * Author : rasintha_j
 * Date : 5/14/2024
 * Time : 2:43 PM
 * Project Name : remindifyapp
 */

package com.app.remindifyapp.services;

import com.app.remindifyapp.dto.SignUpRequest;
import com.app.remindifyapp.entities.User;

public interface AuthenticationService {

    User signup(SignUpRequest signUpRequest);
}
