package com.example.medinsighthub.user.controllers;

import com.example.medinsighthub.user.dto.DoctorDTO;
import com.example.medinsighthub.user.dto.DoctorProfileDTO;
import com.example.medinsighthub.user.services.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/doctor")
@RequiredArgsConstructor
public class DoctorController {
    @Autowired
    private final DoctorService doctorService;

    @GetMapping
    public List<DoctorDTO> getDoctors(){
        return doctorService.getDoctors();
    }

    @GetMapping(path = "profile/{doctor_id}")
    public DoctorProfileDTO getDoctorProfile(@PathVariable(name = "doctor_id") long doctor_id){
        return doctorService.getDoctorProfile(doctor_id);
    }

}
