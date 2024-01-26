package com.example.medinsighthub.user.repositories;

import com.example.medinsighthub.user.Doctor;
import com.example.medinsighthub.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor,Long> {

<<<<<<< HEAD:src/main/java/com/example/medinsighthub/user/repositories/DoctorRepository.java
    @Query("select d from Doctor  d where d.user=?1")
=======
    @Query("select d from Doctor d where d.user=?1")
>>>>>>> 3c2e4af78cb2a6bc3188f9034532f151b09d0b0a:src/main/java/com/example/MedInsightHub/user/repositories/DoctorRepository.java
    Optional<Doctor> getDoctorByUser(User user);
}
