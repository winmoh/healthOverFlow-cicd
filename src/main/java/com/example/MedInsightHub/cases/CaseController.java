package com.example.MedInsightHub.cases;

import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "case")
@RequiredArgsConstructor
public class CaseController {

    private final CaseService caseService;

    @GetMapping
    public List<CaseDTO> getDoctorCases(){
        // TODO get doctor id from request token and pass it as parameter
        long doctor_id=1;
        return caseService.getDoctorCases(doctor_id);
    }

    @PostMapping
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
    public void updateCaseByDoctor(@RequestBody UpdateCase update_case){
        // TODO get doctor id from request token and pass it as parameter
        long doctor_id=1;
        caseService.updateCaseByDoctor(update_case,doctor_id);
    }

    @DeleteMapping("/{case_id}")
    public void deleteCaseByDoctor(@PathVariable("case_id") long case_id){
        // TODO get doctor id from request token and pass it as parameter
        long doctor_id=1;
        caseService.deleteCaseByDoctor(case_id,doctor_id);
    }


}
