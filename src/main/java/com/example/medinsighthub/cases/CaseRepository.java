package com.example.medinsighthub.cases;

import com.example.medinsighthub.user.Doctor;
import com.example.medinsighthub.cases.CaseStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CaseRepository extends JpaRepository<Case , Long> {

    @Query("select c from _case c where c.doctor=?1")
    List<Case> getDoctorCases(Doctor doctor);

    @Query("select c from _case c where c.case_id=?1 and c.doctor=?2")
    Optional<Case> getCaseByIdAndDoctor(long case_id, Doctor doctor);

<<<<<<< HEAD:src/main/java/com/example/medinsighthub/cases/CaseRepository.java
    @Query("select c from _case c where c.doctor=?1 and c.case_status='Resolved'")
=======
    @Query("select c from _case c where c.doctor=?1 and c.case_status=Resolved")
>>>>>>> 3c2e4af78cb2a6bc3188f9034532f151b09d0b0a:src/main/java/com/example/MedInsightHub/cases/CaseRepository.java
    List<Case> getCasesSolvedByDoctor(Doctor doctor);
}
