package com.example.medinsighthub.user.controllers;


import com.example.medinsighthub.user.dto.PatientDTO;
import com.example.medinsighthub.user.dto.PatientProfileDTO;
import com.example.medinsighthub.user.services.PatientService;
import lombok.RequiredArgsConstructor;
<<<<<<< HEAD:src/main/java/com/example/medinsighthub/user/controllers/PatientController.java
import org.springframework.beans.factory.annotation.Autowired;
=======
import org.springframework.security.access.prepost.PreAuthorize;
>>>>>>> 3c2e4af78cb2a6bc3188f9034532f151b09d0b0a:src/main/java/com/example/MedInsightHub/user/controllers/PatientController.java
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/patient")
public class PatientController {
    @Autowired
    private final PatientService patientService;

    @GetMapping
    @PreAuthorize("hasAuthority('Doctor')")
    public List<PatientDTO> getPatients(){
        return patientService.getPatients();
    }

    @GetMapping(path = "profile/{patient_id}")
    @PreAuthorize("hasAuthority('Doctor')")
    public PatientProfileDTO getPatientProfile(@PathVariable long patient_id){
        return patientService.getPatientProfile(patient_id);
    }
}
