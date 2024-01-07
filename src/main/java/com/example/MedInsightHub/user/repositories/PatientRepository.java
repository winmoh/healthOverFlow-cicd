package com.example.MedInsightHub.user.repositories;

import com.example.MedInsightHub.user.Patient;
import com.example.MedInsightHub.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient,Long> {

    @Query("select p from Patient p where p.user=?1")
    Optional<Patient> getPatientByUser(User user);
}
