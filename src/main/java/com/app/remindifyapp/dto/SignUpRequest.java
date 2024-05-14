/**
 * Author : rasintha_j
 * Date : 5/14/2024
 * Time : 2:41 PM
 * Project Name : remindifyapp
 */

package com.app.remindifyapp.dto;

import lombok.Data;

@Data
public class SignUpRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
