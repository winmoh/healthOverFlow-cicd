package com.example.MedInsightHub.user.services;


import com.example.MedInsightHub.cases.CaseRepository;
import com.example.MedInsightHub.user.Doctor;
import com.example.MedInsightHub.user.dto.DoctorDTO;
import com.example.MedInsightHub.user.dto.DoctorProfileDTO;
import com.example.MedInsightHub.user.repositories.DoctorRepository;
import com.example.MedInsightHub.user.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final UserRepository userRepository;
    private final CaseRepository caseRepository;

    public List<DoctorDTO> getDoctors() {
        return doctorRepository.findAll().stream().map(
                doctor -> new DoctorDTO(
                        doctor.getDoctor_id(),
                        doctor.getUser().getUser_id(),
                        doctor.getUser().getProfile_pic_url(),
                        doctor.getSpecialty(),
                        doctor.getYears_of_experience(),
                        doctor.getUser().isOnline()
                )
        ).collect(Collectors.toList());
    }

    public DoctorProfileDTO getDoctorProfile(long doctor_id) {
        Doctor doctor = doctorRepository.findById(doctor_id).orElseThrow();
        DoctorProfileDTO doctorProfileDTO = new DoctorProfileDTO();
        doctorProfileDTO.setDoctor_id(doctor_id);
        doctorProfileDTO.setSpecialty(doctor.getSpecialty());
        doctorProfileDTO.setYears_of_experience(doctor.getYears_of_experience());
        doctorProfileDTO.setFirstname(doctor.getUser().getFirstname());
        doctorProfileDTO.setLastname(doctor.getUser().getLastname());
        doctorProfileDTO.setUsername(doctor.getUser().getUsername());
        doctorProfileDTO.setBio(doctor.getUser().getBio());
        doctorProfileDTO.setEmail(doctor.getUser().getEmail());
        doctorProfileDTO.setRegistration_date(doctor.getUser().getRegistration_date());
        doctorProfileDTO.setLast_login_date(doctor.getUser().getLast_login_date());
        doctorProfileDTO.setProfile_pic_url(doctor.getUser().getProfile_pic_url());
        doctorProfileDTO.setConnections_count(doctor.getUser().getConnections_count());
        doctorProfileDTO.setOnline(doctor.getUser().isOnline());
        doctorProfileDTO.setCases_solved(caseRepository.getCasesSolvedByDoctor(doctor).size());
        return doctorProfileDTO;


    }
    public Doctor getDoctorByUsername(String username) {
        return doctorRepository.getDoctorByUser(userRepository.getUserByUsername(username).orElseThrow()).orElseThrow();
    }
}
