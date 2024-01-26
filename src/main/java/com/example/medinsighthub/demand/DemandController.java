package com.example.MedInsightHub.demand;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/demand")
public class DemandController {
    private final DemandService demandService;
    @PostMapping
    @PreAuthorize("hasAuthority('Patient')")
    public void sendADemand(@RequestBody SendDemandRequest sendDemandRequest){
        long patient_id = 1;
        demandService.sendADemand(patient_id, sendDemandRequest);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('Patient')")
    public List<ConsultDemandsDTO> checkDemands(){
        long patient_id = 1;
        return demandService.checkDemands(patient_id);
    }

    @GetMapping(path = "/all")
    @PreAuthorize("hasAuthority('Doctor')")
    public List<DemandDTO> getAllDemandsByDoctor(){
        long doctor_id = 1;
        return demandService.getAllDemandsByDoctor(doctor_id);
    }

    @PutMapping(path = "/accept/{demand_id}")
    @PreAuthorize("hasAuthority('Doctor')")
    public void acceptDemand(@PathVariable(name = "demand_id") long demand_id){
        long doctor_id = 1;
        demandService.acceptDemand(demand_id, doctor_id);
    }

    @PutMapping(path = "/await/{demand_id}")
    @PreAuthorize("hasAuthority('Doctor')")
    public void awaitDemand(@PathVariable(name = "demand_id") long demand_id){
        long doctor_id = 1;
        demandService.awaitDemand(demand_id, doctor_id);
    }

    @DeleteMapping(path = "/reject/{demand_id}")
    @PreAuthorize("hasAuthority('Doctor')")
    public void rejectDemand(@PathVariable(name = "demand_id") long demand_id){
        long doctor_id = 1;
        demandService.rejectDemand(demand_id, doctor_id);
    }
}
