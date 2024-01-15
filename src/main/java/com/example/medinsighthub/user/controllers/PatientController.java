package com.example.medinsighthub.user.controllers;


import com.example.medinsighthub.user.dto.PatientDTO;
import com.example.medinsighthub.user.dto.PatientProfileDTO;
import com.example.medinsighthub.user.services.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/patient")
public class PatientController {
    private final PatientService patientService;

    @GetMapping
    public List<PatientDTO> getPatients(){
        return patientService.getPatients();
    }

    @GetMapping(path = "profile/{patient_id}")
    public PatientProfileDTO getPatientProfile(@PathVariable(name = "patient_id") long patient_id){
        return patientService.getPatientProfile(patient_id);
    }
}
