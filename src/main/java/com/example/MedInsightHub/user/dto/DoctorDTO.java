package com.example.MedInsightHub.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DoctorDTO {
    private Long doctor_id;
    private Long user_id;
    private String specialty;
    private int years_of_experience;
}
