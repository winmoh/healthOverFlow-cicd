package com.example.MedInsightHub.user.dto;

import com.example.MedInsightHub.user.UserGender;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class PatientProfileDTO {
    private long patient_id;
    private String firstname;
    private String lastname;
    private String username;
    private String bio;
    private String email;
    private LocalDateTime registration_date;
    private LocalDateTime last_login_date;
    private String profile_pic_url;
    private boolean connected_to_a_doctor;
    private boolean online;
    private LocalDate date_of_birth;
    private UserGender gender;
}
