package com.example.MedInsightHub.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Doctor {
    @Id
    @OneToOne
    @JoinColumn(name = "doctor_id" , referencedColumnName = "user_id")
    private User user;
    private String specialty;
    private int years_of_experience;

    public Doctor(String specialty, int years_of_experience) {
        this.specialty = specialty;
        this.years_of_experience = years_of_experience;
    }
}
