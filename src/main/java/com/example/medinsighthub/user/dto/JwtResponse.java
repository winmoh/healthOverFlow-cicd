package com.example.MedInsightHub.user.dto;

import com.example.MedInsightHub.user.UserType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class JwtResponse {
    private String username;
    private String firstname;
    private String lastname;
    private String profile_pic_url;
    private String token;
    private UserType user_type;
}
