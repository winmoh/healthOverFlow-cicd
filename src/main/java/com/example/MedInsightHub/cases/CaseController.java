package com.example.MedInsightHub.cases;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "case")
@RequiredArgsConstructor
public class CaseController {

    private CaseService caseService;

    @GetMapping
    public List<Case> getDoctorCases(){
        // TODO get doctor id from request token and pass it as parameter
        long doctor_id=0;
        return caseService.getDoctorCases(doctor_id);
    }

    @PostMapping
    public void createCaseByDoctor(@RequestParam Case a_case){
        caseService.createCaseByDoctor(a_case);
    }

    @PutMapping
    public void updateCaseByDoctor(@RequestParam UpdateCase update_case){
        // TODO get doctor id from request token and pass it as parameter
        long doctor_id=0;
        caseService.updateCaseByDoctor(update_case,doctor_id);
    }

    @DeleteMapping
    public void deleteCaseByDoctor(@RequestParam long case_id){
        // TODO get doctor id from request token and pass it as parameter
        long doctor_id=0;
        caseService.deleteCaseByDoctor(case_id,doctor_id);
    }


}
