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
    @OneToOne
    @JoinColumn( name = "patient_id", referencedColumnName = "patient_id")
    private Patient patient;
    @OneToOne
    @JoinColumn( name = "doctor_id", referencedColumnName = "doctor_id")
    private Doctor doctor;
    private DemandStatus demand_status;
    private String demand_document_url;

    public Demand(Patient patient, Doctor doctor, DemandStatus demand_status, String demand_document_url) {
        this.patient = patient;
        this.doctor = doctor;
        this.demand_status = demand_status;
        this.demand_document_url = demand_document_url;
    }
}
