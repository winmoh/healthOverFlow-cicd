package com.example.medinsighthub.user.dto;

import com.example.medinsighthub.user.UserGender;
import com.example.medinsighthub.user.UserType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserDTO {
    private long user_id;
    private String firstname;
    private String lastname;
    private String username;
    private String bio;
    private String email;
    private UserType user_type;
    private LocalDateTime registration_date;
    private String profile_pic_url;
    private int connections_count;
    private String doctor_specialty;
    private int doctor_years_of_experience;
    private LocalDateTime patient_date_of_birth;
    private UserGender patient_gender;

}
