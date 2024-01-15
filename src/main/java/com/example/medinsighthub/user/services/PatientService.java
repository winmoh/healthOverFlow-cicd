package com.example.medinsighthub.user.services;

import com.example.medinsighthub.user.Patient;
import com.example.medinsighthub.user.dto.PatientDTO;
import com.example.medinsighthub.user.dto.PatientProfileDTO;
import com.example.medinsighthub.user.repositories.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PatientService {
    @Autowired
    private final PatientRepository patientRepository;

    public List<PatientDTO> getPatients() {
        return patientRepository.findAll().stream().map(
                patient -> new PatientDTO(
                        patient.getPatient_id(),
                        patient.getUser().getUser_id(),
                        patient.getUser().getFirstname(),
                        patient.getUser().getLastname(),
                        patient.getUser().getProfile_pic_url(),
                        patient.getGender(),
                        patient.getUser().isOnline()
                )
        ).collect(Collectors.toList());
    }

    public PatientProfileDTO getPatientProfile(long patient_id) {
        PatientProfileDTO patientProfileDTO = new PatientProfileDTO();
        Patient patient = patientRepository.findById(patient_id).orElseThrow(
                () -> new IllegalStateException("patient with id "+patient_id+" was not found!!!")
        );

        patientProfileDTO.setPatient_id(patient_id);
        patientProfileDTO.setFirstname(patient.getUser().getFirstname());
        patientProfileDTO.setLastname(patient.getUser().getLastname());
        patientProfileDTO.setUsername(patient.getUser().getUsername());
        patientProfileDTO.setBio(patient.getUser().getBio());
        patientProfileDTO.setEmail(patient.getUser().getEmail());
        patientProfileDTO.setRegistration_date(patient.getUser().getRegistration_date());
        patientProfileDTO.setLast_login_date(patient.getUser().getLast_login_date());
        patientProfileDTO.setProfile_pic_url(patient.getUser().getProfile_pic_url());
        patientProfileDTO.setConnected_to_a_doctor(patient.getUser().getConnections_count()!=0);
        patientProfileDTO.setOnline(patient.getUser().isOnline());
        patientProfileDTO.setDate_of_birth(patient.getDate_of_birth());
        patientProfileDTO.setGender(patient.getGender());

        return patientProfileDTO;
    }
}
