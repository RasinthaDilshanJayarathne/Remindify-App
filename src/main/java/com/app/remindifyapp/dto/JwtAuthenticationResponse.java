/**
 * Author : rasintha_j
 * Date : 5/14/2024
 * Time : 3:22 PM
 * Project Name : remindifyapp
 */

package com.app.remindifyapp.dto;

import lombok.Data;

@Data
public class JwtAuthenticationResponse {

    private String token;
    private String refreshToken;
}
