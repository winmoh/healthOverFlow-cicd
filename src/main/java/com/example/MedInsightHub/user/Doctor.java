package com.example.MedInsightHub.user;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Doctor {
    @Id
    @SequenceGenerator(
            name = "doctor_sequence",
            sequenceName = "doctor_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "doctor_sequence"
    )
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
