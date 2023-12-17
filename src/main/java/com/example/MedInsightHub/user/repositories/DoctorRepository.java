package com.example.MedInsightHub.user.repositories;

import com.example.MedInsightHub.user.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor,Long> {
}
