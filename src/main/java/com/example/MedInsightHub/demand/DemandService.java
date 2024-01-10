package com.example.MedInsightHub.demand;

import com.example.MedInsightHub.connection.Connection;
import com.example.MedInsightHub.connection.ConnectionRepository;
import com.example.MedInsightHub.user.Patient;
import com.example.MedInsightHub.user.User;
import com.example.MedInsightHub.user.UserType;
import com.example.MedInsightHub.user.repositories.DoctorRepository;
import com.example.MedInsightHub.user.repositories.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DemandService {
    private final DemandRepository demandRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final ConnectionRepository connectionRepository;

    public void sendADemand(long patient_id, SendDemandRequest sendDemandRequest) {
        Patient patient = patientRepository.findById(patient_id).orElseThrow();
        if (patient.getUser().getConnections_count()==0) {
            throw new IllegalStateException("patient with id "+patient_id+" is not connected to any doctor");
        }
        Connection connection = connectionRepository.findConnectionsByUser(patient.getUser()).get(0);
        User doctor = connection.getUser_sender().getUser_type()== UserType.Doctor ? connection.getUser_sender() : connection.getUser_receiver();
        Demand demand = new Demand();
        demand.setDemand_status(DemandStatus.Pending);
        demand.setDoctor(doctorRepository.getDoctorByUser(doctor).orElseThrow());
        demand.setPatient(patient);
        demand.setDemand_message(sendDemandRequest.getDemand_message());
        demand.setDemand_document_url(sendDemandRequest.getDemand_document_url());
        demand.setDemand_date_sent(LocalDateTime.now());
        demandRepository.save(demand);

    }

    public List<DemandDTO> getAllDemandsByDoctor(long doctor_id) {
        return demandRepository.getDemandsByDoctor(doctorRepository.findById(doctor_id).orElseThrow()).stream().map(
                demand -> new DemandDTO(
                        demand.getDemand_id(),
                        demand.getPatient().getUser().getUser_id(),
                        demand.getPatient().getUser().getFirstname(),
                        demand.getPatient().getUser().getLastname(),
                        demand.getPatient().getUser().getUsername(),
                        demand.getDemand_message(),
                        demand.getDemand_document_url()!=null,
                        demand.getDemand_status(),
                        demand.getDemand_date_sent()
                )
        ).collect(Collectors.toList());
    }

    public void acceptDemand(long demand_id, long doctor_id) {
        Demand demand = demandRepository.findById(demand_id).orElseThrow(
                () -> new IllegalStateException("demand with id "+demand_id+" was not found")
        );
        if (demand.getDoctor().getDoctor_id()!=demand_id) {
            throw new IllegalStateException("doctor with id "+doctor_id+" does not have the permission to accept the specified demand");
        }
        demand.setDemand_status(DemandStatus.Approved);
        demandRepository.save(demand);
        // TODO notification demand accepted or approved
    }


    public void awaitDemand(long demand_id, long doctor_id) {
        Demand demand = demandRepository.findById(demand_id).orElseThrow(
                () -> new IllegalStateException("demand with id "+demand_id+" was not found")
        );
        if (demand.getDoctor().getDoctor_id()!=demand_id) {
            throw new IllegalStateException("doctor with id "+doctor_id+" does not have any permission on the specified demand");
        }
        demand.setDemand_status(DemandStatus.Await);
        demandRepository.save(demand);
        // TODO notification demand to wait
    }

    public void rejectDemand(long demand_id, long doctor_id) {
        Demand demand = demandRepository.findById(demand_id).orElseThrow(
                () -> new IllegalStateException("demand with id "+demand_id+" was not found")
        );
        if (demand.getDoctor().getDoctor_id()!=demand_id) {
            throw new IllegalStateException("doctor with id "+doctor_id+" does not have permission to reject the specified demand");
        }
        demandRepository.delete(demand);
        // TODO notification demand rejected
    }

    public List<ConsultDemandsDTO> checkDemands(long patient_id) {
        return demandRepository.getDemandsByPatient(patientRepository.findById(patient_id).orElseThrow()).stream().map(
                demand -> new ConsultDemandsDTO(
                        demand.getDemand_id(),
                        demand.getDemand_message(),
                        demand.getDemand_document_url()!=null,
                        demand.getDemand_status(),
                        demand.getDemand_date_sent()
                )
        ).collect(Collectors.toList());
    }
}
