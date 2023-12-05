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
    @OneToOne
    @JoinColumn(name = "patient_id" , referencedColumnName = "user_id")
    private User user;
    private Date date_of_birth;
    @Enumerated(value = EnumType.STRING)
    private UserGender gender;

    public Patient(Date date_of_birth, UserGender gender) {
        this.date_of_birth = date_of_birth;
        this.gender = gender;
    }
}
