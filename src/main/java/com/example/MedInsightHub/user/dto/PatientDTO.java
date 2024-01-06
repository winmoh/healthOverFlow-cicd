package com.example.MedInsightHub.user.dto;

import com.example.MedInsightHub.user.UserGender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PatientDTO {
    private long patient_id;
    private long user_id;
    private String patient_firstname;
    private String patient_lastname;
    private String profile_pic_url;
    private UserGender patient_gender;
    private boolean online;
}
