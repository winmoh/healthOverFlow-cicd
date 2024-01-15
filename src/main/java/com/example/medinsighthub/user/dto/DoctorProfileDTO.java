package com.example.medinsighthub.user.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class DoctorProfileDTO {
    private long doctor_id;
    private String specialty;
    private int years_of_experience;
    private String firstname;
    private String lastname;
    private String username;
    private String bio;
    private String email;
    private LocalDateTime registration_date;
    private LocalDateTime last_login_date;
    private String profile_pic_url;
    private int connections_count;
    private boolean online;
    private long cases_solved;
}
