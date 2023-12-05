package com.example.MedInsightHub.demand;

import com.example.MedInsightHub.user.Doctor;
import com.example.MedInsightHub.user.Patient;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Demand {
    @Id
    @SequenceGenerator(
            name = "demand_sequence",
            sequenceName = "demand_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "demand_sequence"
    )
    private long demand_id;
    private Patient patient;
    private Doctor doctor;
    private DemandStatus demand_status;

    public Demand(Patient patient, Doctor doctor, DemandStatus demand_status) {
        this.patient = patient;
        this.doctor = doctor;
        this.demand_status = demand_status;
    }
}
