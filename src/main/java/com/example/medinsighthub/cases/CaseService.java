package com.example.medinsighthub.cases;

import com.example.medinsighthub.user.Doctor;
import com.example.medinsighthub.user.repositories.DoctorRepository;
import com.example.medinsighthub.user.repositories.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CaseService {

    private final CaseRepository caseRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    public boolean caseBelongsToDoctor(Case a_case, long doctor_id){
        Optional<Case> check_case = caseRepository.getCaseByIdAndDoctor(a_case.getCase_id(),doctorRepository.findById(doctor_id).orElseThrow());
        return check_case.isPresent();
    }

    public List<CaseDTO> getDoctorCases(long doctor_id) {
        Doctor doctor = doctorRepository.findById(doctor_id).orElseThrow();
        return caseRepository.getDoctorCases(doctor).stream().map(
                aCase -> new CaseDTO(
                        aCase.getCase_id(),
                        aCase.getPatient().getPatient_id(),
                        aCase.getAnalysis_content(),
                        aCase.getCase_document_url(),
                        aCase.getCase_status()
                )
        ).collect(Collectors.toList());
    }
    public void createCaseByDoctor(long doctor_id, String analysis_content, String case_document_url) {
        Case a_case = new Case();
        a_case.setCase_status(CaseStatus.Pending);
        a_case.setDoctor(doctorRepository.findById(doctor_id).orElseThrow());
        a_case.setAnalysis_content(analysis_content);
        a_case.setCase_document_url(case_document_url);
        caseRepository.save(a_case);
        // TODO notification
    }

    public void createCaseByDoctor(long doctor_id, long patient_id, String analysis_content, String case_document_url) {
        Case a_case = new Case();
        a_case.setCase_status(CaseStatus.Pending);
        a_case.setDoctor(doctorRepository.findById(doctor_id).orElseThrow());
        a_case.setPatient(patientRepository.findById(patient_id).orElseThrow());
        a_case.setAnalysis_content(analysis_content);
        a_case.setCase_document_url(case_document_url);
        caseRepository.save(a_case);
        // TODO notification
    }

    public void updateCaseByDoctor(UpdateCase update_case, long doctor_id){
        Case a_case = caseRepository.findById(update_case.case_id).orElseThrow(
                () -> new IllegalStateException("case with id "+update_case.case_id+" not found!!!")
        );
        if(!caseBelongsToDoctor(a_case,doctor_id)){
            throw new IllegalStateException("case with id "+a_case.getCase_id()+" not found fo doctor with id "+doctor_id);
        }
        if (update_case.case_status!=null){
            a_case.setCase_status(update_case.case_status);
        } if (update_case.analysis_content!=null && !update_case.analysis_content.isEmpty()){
            a_case.setAnalysis_content(update_case.analysis_content);
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
        // TODO remove posts related to case first
        caseRepository.delete(a_case);
        // TODO notification
    }
}
