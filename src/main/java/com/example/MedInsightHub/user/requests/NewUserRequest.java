package com.example.MedInsightHub.user.requests;

import com.example.MedInsightHub.user.UserGender;
import com.example.MedInsightHub.user.UserType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class NewUserRequest {
    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private String email;
    private UserType user_type;
    private String doctor_specialty;
    private Integer doctor_years_of_experience;
    private LocalDate patient_date_of_birth;
    private UserGender patient_gender;
}
