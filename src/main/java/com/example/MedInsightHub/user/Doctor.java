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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long doctor_id;

    @OneToOne
    @JoinColumn(name = "user_id" , referencedColumnName = "user_id")
    private User user;
    private String specialty;
    private int years_of_experience;

    public Doctor(User user, String specialty, int years_of_experience) {
        this.user = user;
        this.specialty = specialty;
        this.years_of_experience = years_of_experience;
    }
}
