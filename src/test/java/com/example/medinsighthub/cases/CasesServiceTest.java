package com.example.medinsighthub.cases;

import com.example.medinsighthub.user.*;
import com.example.medinsighthub.user.repositories.DoctorRepository;
import com.example.medinsighthub.user.repositories.PatientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

import static com.example.medinsighthub.cases.CaseStatus.Pending;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class CasesServiceTest {
    @InjectMocks
    CaseService caseService;

    @Mock
    CaseRepository caseRepository;
    @Mock
    DoctorRepository doctorRepository;

    @Mock
    PatientRepository patientRepository;

    List<Case> doctorCases;
    AtomicBoolean methodCalled;
    Doctor doc;
    User user ;
    Patient patient;
    @BeforeEach
    public void setUp(){
         patient=Patient.builder()
                .patient_id(1L)
                .gender(UserGender.Male)
                .build();
        doc =Doctor.builder()
                .doctor_id(1L)

                .specialty("pediatre")
                .years_of_experience(4)

        .build();
        User.builder()
                .firstname("John")
                .lastname("Doe")
                .username("johndoe")
                .password("password123")
                .bio("Some bio")
                .email("john.doe@example.com")
                .user_type(UserType.Patient)
                .registration_date(LocalDateTime.now())
                .last_login_date(LocalDateTime.now())
                .profile_pic_url("http://example.com/profile.jpg")
                .connections_count(0)
                .online(false)
                .build();
        doctorCases = new ArrayList<Case>();
        methodCalled=new AtomicBoolean(false);
        // Adding CaseDTO objects to the list
        doctorCases.add(Case.builder()
                .case_id(1)
                        .patient(patient)
                .analysis_content("Analysis content for case 1")
                .case_document_url("http://example.com/case1")
                .case_status(Pending)
                .build());

        doctorCases.add(Case.builder()
                .case_id(2)
                        .patient(patient)
                .analysis_content("Analysis content for case 2")
                .case_document_url("http://example.com/case2")
                .case_status(CaseStatus.Resolved)
                .build());

    }

    @Test
    public void getDoctorCases(){
        //mocking
        when(doctorRepository.findById(1L)).thenReturn(Optional.ofNullable(doc));
        when(caseRepository.getDoctorCases(doc)).thenReturn(doctorCases);
        //calling the service
        List<CaseDTO> cases=caseService.getDoctorCases(1L);


        assertThat(cases).isEqualTo(doctorCases.stream().map(
                acase-> CaseDTO.builder()
                            .case_id(acase.getCase_id())
                        .patient_id(acase.getPatient().getPatient_id())
                            .analysis_content(acase.getAnalysis_content())
                            .case_document_url(acase.getCase_document_url())
                            .case_status(acase.getCase_status())
                            .build()).collect(Collectors.toList()));


    }

    @Test
    public void createCaseByDoctorServiceTest(){
        Case cas=Case.builder()
                .case_status(Pending)
                .doctor(doc)
                .analysis_content("analysis done")
                .case_document_url("http:/doctor is not found").build();
        //mocking the repo methods
        when(doctorRepository.findById(1L)).thenReturn(Optional.ofNullable(doc));
        doAnswer(ele->{
            methodCalled.set(true);
            return null;
        }).when(caseRepository).save(any(Case.class));

        caseService.createCaseByDoctor(cas.getDoctor().getDoctor_id(), cas.getAnalysis_content(),cas.getCase_document_url());
        assert(methodCalled.get());
    }

    @Test
    public void secondCreateCaseByDoctorTest(){
        //mocking the used method within the service
        when(doctorRepository.findById(1L)).thenReturn(Optional.ofNullable(doc));
        when(patientRepository.findById(1L)).thenReturn(Optional.ofNullable(patient));

        doAnswer(tr->{
            methodCalled.set(true);
            return null;
        }).when(caseRepository).save(any(Case.class));
       caseService.createCaseByDoctor(1L,1L,"analysiss was done","http:/mysql:databe/analysis");

       assert(methodCalled.get());
    }

    @Test
    public void updateCaseByDoctorTest(){

    }
}


