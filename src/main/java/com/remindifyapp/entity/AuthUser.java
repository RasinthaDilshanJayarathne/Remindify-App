package com.remindifyapp.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Builder
@Document("user")
public class AuthUser {

    @Id
    private String username;
    private String email;
    private String password;
    private Date birthday;
    private String image;
    private boolean active;
}
