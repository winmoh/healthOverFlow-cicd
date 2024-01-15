package com.example.medinsighthub.user;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;


@Entity
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Patient {
    @Id
    @SequenceGenerator(
            name = "patient_sequence",
            sequenceName = "patient_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "patient_sequence"
    )
    private Long patient_id;

    @OneToOne
    @JoinColumn(name = "user_id" , referencedColumnName = "user_id")
    private User user;
    private LocalDate date_of_birth;
    @Enumerated(value = EnumType.STRING)
    private UserGender gender;

    public Patient(User user, LocalDate date_of_birth, UserGender gender) {
        this.user = user;
        this.date_of_birth = date_of_birth;
        this.gender = gender;
    }
}
