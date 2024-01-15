package com.example.medinsighthub.cases;

import com.example.medinsighthub.user.Doctor;
import com.example.medinsighthub.user.Patient;
import jakarta.persistence.*;
import lombok.*;

@Entity( name = "_case" )
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
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
    @ManyToOne
    @JoinColumn(name = "doctor_id", referencedColumnName = "doctor_id")
    private Doctor doctor;
    @ManyToOne
    @JoinColumn(name = "patient_id", referencedColumnName = "patient_id")
    private Patient patient;
    private String analysis_content;
    private String case_document_url;
    @Enumerated(value = EnumType.STRING)
    private CaseStatus case_status;


    public Case(Doctor doctor, Patient patient, String analysis_content, String case_document_url, CaseStatus case_status) {
        this.doctor = doctor;
        this.patient = patient;
        this.analysis_content = analysis_content;
        this.case_document_url = case_document_url;
        this.case_status = case_status;
    }
}
