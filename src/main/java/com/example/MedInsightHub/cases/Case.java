package com.example.MedInsightHub.cases;

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
public class Case {
    @Id
    @SequenceGenerator(
            name = "case_sequence",
            sequenceName = "case_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "case_sequence"
    )
    private long case_id;
    @OneToMany
    @JoinColumn(name = "doctor_id", referencedColumnName = "doctor_id")
    private Doctor doctor;
    @ManyToOne
    @JoinColumn(name = "patient_id", referencedColumnName = "patient_id")
    private Patient patient;
    private String analysis_content;
    private boolean include_document;
    private String case_document_url;
    private CaseStatus case_status;


    public Case(Doctor doctor, Patient patient, String analysis_content, boolean include_document, String case_document_url, CaseStatus case_status) {
        this.doctor = doctor;
        this.patient = patient;
        this.analysis_content = analysis_content;
        this.include_document = include_document;
        this.case_document_url = case_document_url;
        this.case_status = case_status;
    }
}
