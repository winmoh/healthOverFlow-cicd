package com.example.medinsighthub.user.repositories;

import com.example.medinsighthub.user.Doctor;
import com.example.medinsighthub.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor,Long> {

    @Query("select d from doctor d where d.user=?1")
    Optional<Doctor> getDoctorByUser(User user);
}
