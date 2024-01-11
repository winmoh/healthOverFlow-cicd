package com.example.MedInsightHub.user.services;

import com.example.MedInsightHub.user.*;
import com.example.MedInsightHub.user.dto.JwtResponse;
import com.example.MedInsightHub.user.dto.UserDTO;
import com.example.MedInsightHub.user.repositories.DoctorRepository;
import com.example.MedInsightHub.user.repositories.PatientRepository;
import com.example.MedInsightHub.user.repositories.UserRepository;
import com.example.MedInsightHub.user.requests.AuthenticationRequest;
import com.example.MedInsightHub.user.requests.NewUserRequest;
import com.example.MedInsightHub.user.requests.UpdateProfileRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;


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
            userDTO.setPatient_date_of_birth(patientRepository.getPatientByUser(user).orElseThrow().getDate_of_birth());
        }

        return userDTO;
    }

    public void updateProfile(UpdateProfileRequest updateProfileRequest, long user_id) {
        User user = userRepository.findById(user_id).orElseThrow();
        if (updateProfileRequest.getFirstname()!=null && !updateProfileRequest.getFirstname().isEmpty() && !updateProfileRequest.getFirstname().equals(user.getFirstname())){
            user.setFirstname(updateProfileRequest.getFirstname());
        }
        if(updateProfileRequest.getLastname()!=null && !updateProfileRequest.getLastname().isEmpty() && !updateProfileRequest.getLastname().equals(user.getLastname())){
            user.setLastname(updateProfileRequest.getLastname());
        }
        if(updateProfileRequest.getUsername()!=null && !updateProfileRequest.getUsername().isEmpty() && !updateProfileRequest.getUsername().equals(user.getUsername())){
            user.setUsername(updateProfileRequest.getUsername());
        }
        if(updateProfileRequest.getBio()!=null && !updateProfileRequest.getBio().isEmpty() && !updateProfileRequest.getBio().equals(user.getBio())){
            user.setBio(updateProfileRequest.getBio());
        }
        if(updateProfileRequest.getEmail()!=null && !updateProfileRequest.getEmail().isEmpty() && !updateProfileRequest.getEmail().equals(user.getEmail())){
            user.setEmail(updateProfileRequest.getEmail());
        }
        if(updateProfileRequest.getProfile_pic_url()!=null && !updateProfileRequest.getProfile_pic_url().isEmpty() && !updateProfileRequest.getProfile_pic_url().equals(user.getProfile_pic_url())){
            user.setProfile_pic_url(updateProfileRequest.getProfile_pic_url());
        }
        userRepository.save(user);
        if (updateProfileRequest.getUser_type()==UserType.Doctor) {
            updateDoctor(user, updateProfileRequest.getDoctor_specialty(), updateProfileRequest.getDoctor_years_of_experience());
        } else {
            updatePatient(user, updateProfileRequest.getPatient_date_of_birth());
        }
    }

    private void updateDoctor(User user, String doctor_speciality, Integer doctor_years_of_experience){
        Doctor doctor = doctorRepository.getDoctorByUser(user).orElseThrow();
        if (doctor_speciality!=null && !doctor_speciality.isEmpty()) {
            doctor.setSpecialty(doctor_speciality);
        }
        if (doctor_years_of_experience!=null) {
            doctor.setYears_of_experience(doctor_years_of_experience);
        }
        doctorRepository.save(doctor);
    }

    private void updatePatient(User user, LocalDate patient_date_of_birth){
        Patient patient = patientRepository.getPatientByUser(user).orElseThrow();
        if(patient_date_of_birth!=null){
            patient.setDate_of_birth(patient_date_of_birth);
        }
        patientRepository.save(patient);
    }

    public String getEncodedPassword(String password){
        return passwordEncoder.encode(password);
    }


    public ResponseEntity<JwtResponse> newUser(NewUserRequest newUserRequest) {
        User user = new User();
        user.setFirstname(newUserRequest.getFirstname());
        user.setLastname(newUserRequest.getLastname());
        user.setUsername(newUserRequest.getUsername());
        user.setEmail(newUserRequest.getEmail());
        user.setPassword(getEncodedPassword(newUserRequest.getPassword()));
        user.setUser_type(newUserRequest.getUser_type());
        user.setRegistration_date(LocalDateTime.now());
        userRepository.save(user);
        if (newUserRequest.getUser_type()==UserType.Doctor) {
            Doctor doctor = new Doctor();
            doctor.setUser(user);
            doctor.setSpecialty(newUserRequest.getDoctor_specialty());
            doctor.setYears_of_experience(newUserRequest.getDoctor_years_of_experience());
            doctorRepository.save(doctor);
        } else {
            Patient patient = new Patient();
            patient.setUser(user);
            patient.setGender(newUserRequest.getPatient_gender());
            patient.setDate_of_birth(newUserRequest.getPatient_date_of_birth());
            patientRepository.save(patient);
        }
        JwtResponse jwtResponse = new JwtResponse(
                user.getUsername(),
                user.getFirstname(),
                user.getLastname(),
                user.getProfile_pic_url(),
                jwtService.generateToken(user.getUsername()),
                user.getUser_type()
        );
        return new ResponseEntity<>(jwtResponse, HttpStatus.OK);
    }

    public ResponseEntity<JwtResponse> auth(AuthenticationRequest authenticationRequest) {
        User a_user = userRepository.getUserByUsername(authenticationRequest.getUsername()).orElseThrow();
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getUsername(),
                        authenticationRequest.getPassword()
                )
        );
        JwtResponse jwtResponse = new JwtResponse(
                a_user.getUsername(),
                a_user.getFirstname(),
                a_user.getLastname(),
                a_user.getProfile_pic_url(),
                jwtService.generateToken(getEncodedPassword(authenticationRequest.getUsername())),
                a_user.getUser_type()
        );
        return new ResponseEntity<>(jwtResponse, HttpStatus.OK);
    }

    public long usernameToUserId(String username) {
        return userRepository.getUserByUsername(username).orElseThrow().getUser_id();
    }
}
