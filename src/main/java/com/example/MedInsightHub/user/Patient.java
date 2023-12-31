package com.example.MedInsightHub.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;


@Entity
@Getter
@Setter
@NoArgsConstructor
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
    private Date date_of_birth;
    @Enumerated(value = EnumType.STRING)
    private UserGender gender;

    public Patient(User user, Date date_of_birth, UserGender gender) {
        this.user = user;
        this.date_of_birth = date_of_birth;
        this.gender = gender;
    }
}
