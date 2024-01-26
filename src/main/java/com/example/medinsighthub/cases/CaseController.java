package com.example.medinsighthub.cases;

import lombok.RequiredArgsConstructor;
<<<<<<< HEAD:src/main/java/com/example/medinsighthub/cases/CaseController.java
import org.springframework.beans.factory.annotation.Autowired;
=======
import org.springframework.security.access.prepost.PreAuthorize;
>>>>>>> 3c2e4af78cb2a6bc3188f9034532f151b09d0b0a:src/main/java/com/example/MedInsightHub/cases/CaseController.java
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "case")
@RequiredArgsConstructor
public class CaseController {

    @Autowired
    private  CaseService caseService;

    @GetMapping
    @PreAuthorize("hasAuthority('Doctor')")
    public List<CaseDTO> getDoctorCases(){
        // TODO get doctor id from request token and pass it as parameter
        long doctor_id=1;
        return caseService.getDoctorCases(doctor_id);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('Doctor')")
    public void createCaseByDoctor(@RequestBody CaseRequest caseRequest) {
        // TODO get doctor id from request token and pass it as parameter
        long doctor_id = 1;
        if (caseRequest.getPatient_id() == null) {
            caseService.createCaseByDoctor(doctor_id, caseRequest.getAnalysis_content(), caseRequest.getCase_document_url());
        } else {
            caseService.createCaseByDoctor(doctor_id, caseRequest.getPatient_id(), caseRequest.getAnalysis_content(), caseRequest.getCase_document_url());
        }
    }

    @PutMapping
    @PreAuthorize("hasAuthority('Doctor')")
    public void updateCaseByDoctor(@RequestBody UpdateCase update_case){
        // TODO get doctor id from request token and pass it as parameter
        long doctor_id=1;
        caseService.updateCaseByDoctor(update_case,doctor_id);
    }

    @DeleteMapping("/{case_id}")
    @PreAuthorize("hasAuthority('Doctor')")
    public void deleteCaseByDoctor(@PathVariable("case_id") long case_id){
        // TODO get doctor id from request token and pass it as parameter
        long doctor_id=1;
        caseService.deleteCaseByDoctor(case_id,doctor_id);
    }


}
