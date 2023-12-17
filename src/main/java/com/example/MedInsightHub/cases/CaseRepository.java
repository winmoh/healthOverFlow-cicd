package com.example.MedInsightHub.cases;

import com.example.MedInsightHub.user.Doctor;
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
}
