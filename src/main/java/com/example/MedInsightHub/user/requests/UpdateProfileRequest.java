package com.example.MedInsightHub.user.requests;

import com.example.MedInsightHub.user.UserType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UpdateProfileRequest {
    private String firstname;
    private String lastname;
    private String username;
    private String bio;
    private String email;
    private String profile_pic_url;
    private UserType user_type;
    private String doctor_specialty;
    private Integer doctor_years_of_experience;
    private LocalDate patient_date_of_birth;
}
