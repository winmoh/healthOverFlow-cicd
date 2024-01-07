package com.example.MedInsightHub.demand;

import com.example.MedInsightHub.user.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DemandRepository extends JpaRepository<Demand, Long> {

    @Query("select d from Demand d where d.doctor=?1")
    List<Demand> getDemandsByDoctor(Doctor doctor);
}
