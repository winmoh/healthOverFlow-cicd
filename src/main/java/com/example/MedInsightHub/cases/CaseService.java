package com.example.MedInsightHub.cases;

import com.example.MedInsightHub.user.Doctor;
import com.example.MedInsightHub.user.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CaseService {

    private CaseRepository caseRepository;
    private DoctorRepository doctorRepository;

    public boolean caseBelongsToDoctor(Case a_case, long doctor_id){
        Optional<Case> check_case = caseRepository.getCaseByIdAndDoctor(a_case.getCase_id(),doctorRepository.findById(doctor_id).orElseThrow());
        return check_case.isPresent();
    }

    public List<Case> getDoctorCases(long doctor_id) {
        Doctor doctor = doctorRepository.findById(doctor_id).orElseThrow();
        return caseRepository.getDoctorCases(doctor);
    }

    public void createCaseByDoctor(Case a_case) {
        caseRepository.save(a_case);
        // TODO notification
    }

    public void updateCaseByDoctor(UpdateCase update_case, long doctor_id){
        Case a_case = caseRepository.findById(update_case.case_id).orElseThrow(
                () -> new IllegalStateException("case with id "+update_case.case_id+" not found!!!!")
        );
        if(!caseBelongsToDoctor(a_case,doctor_id)){
            throw new IllegalStateException("case with id "+a_case.getCase_id()+" not found fo doctor with id "+doctor_id);
        }
        caseRepository.save(a_case);
        // TODO notification
    }

    public void deleteCaseByDoctor(long case_id, long doctor_id) {
        Case a_case = caseRepository.findById(case_id).orElseThrow(
                () -> new IllegalStateException("case with id "+case_id+" not found!!!!")
        );
        if(!caseBelongsToDoctor(a_case,doctor_id)){
            throw new IllegalStateException("case with id "+a_case.getCase_id()+" not found fo doctor with id "+doctor_id);
        }
        caseRepository.deleteById(case_id);
        // TODO notification
    }
}
