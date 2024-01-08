package com.example.MedInsightHub.user.services;

import com.example.MedInsightHub.user.User;
import com.example.MedInsightHub.user.UserType;
import com.example.MedInsightHub.user.dto.UserDTO;
import com.example.MedInsightHub.user.repositories.DoctorRepository;
import com.example.MedInsightHub.user.repositories.PatientRepository;
import com.example.MedInsightHub.user.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;


    public UserDTO getUserInfo(long user_id) {
        User user = userRepository.findById(user_id).orElseThrow();
        UserDTO userDTO = new UserDTO();
        userDTO.setUser_id(user_id);
        userDTO.setFirstname(user.getFirstname());
        userDTO.setLastname(user.getLastname());
        userDTO.setUsername(user.getUsername());
        userDTO.setBio(user.getBio());
        userDTO.setEmail(user.getEmail());
        userDTO.setUser_type(user.getUser_type());
        userDTO.setRegistration_date(user.getRegistration_date());
        userDTO.setProfile_pic_url(user.getProfile_pic_url());
        userDTO.setConnections_count(user.getConnections_count());

        if (user.getUser_type()== UserType.Doctor) {
            userDTO.setDoctor_specialty(doctorRepository.getDoctorByUser(user).orElseThrow().getSpecialty());
            userDTO.setDoctor_years_of_experience(doctorRepository.getDoctorByUser(user).orElseThrow().getYears_of_experience());
        } else {
            userDTO.setPatient_gender(patientRepository.getPatientByUser(user).orElseThrow().getGender());
            userDTO.setPatient_date_of_birth(LocalDateTime.of(patientRepository.getPatientByUser(user).orElseThrow().getDate_of_birth(), LocalTime.MIDNIGHT));
        }

        return userDTO;
    }
}
