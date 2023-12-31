package com.example.MedInsightHub.user.services;


import com.example.MedInsightHub.user.dto.DoctorDTO;
import com.example.MedInsightHub.user.repositories.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorRepository doctorRepository;

    public List<DoctorDTO> getDoctors() {
        return doctorRepository.findAll().stream().map(
                doctor -> new DoctorDTO(
                    doctor.getDoctor_id(),
                    doctor.getUser().getUser_id(),
                    doctor.getSpecialty(),
                    doctor.getYears_of_experience()
                )
        ).collect(Collectors.toList());
    }
}
