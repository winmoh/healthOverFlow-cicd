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
        return caseService.getDoctorCases(0);
    }

    @PostMapping
    public void createCaseByDoctor(@RequestParam Case a_case){
        caseService.createCaseByDoctor(a_case);
    }

    @PutMapping
    public void updateCaseByDoctor(@RequestParam UpdateCase update_case){
        // TODO get doctor id from request token and pass it as parameter
        caseService.updateCaseByDoctor(update_case,0);
    }

    @DeleteMapping
    public void deleteCaseByDoctor(@RequestParam long case_id){
        // TODO get doctor id from request token and pass it as parameter
        caseService.deleteCaseByDoctor(case_id,0);
    }


}
