package com.example.medinsighthub.user.repositories;

import com.example.medinsighthub.user.Patient;
import com.example.medinsighthub.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient,Long> {

    @Query("select p from Patient p where p.user=?1")
    Optional<Patient> getPatientByUser(User user);
}
