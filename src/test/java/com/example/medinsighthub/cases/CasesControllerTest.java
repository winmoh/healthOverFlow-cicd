package com.example.medinsighthub.cases;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.example.medinsighthub.cases.CaseStatus.Resolved;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class CasesControllerTest {
    List<CaseDTO> cases;

    @InjectMocks
    CaseController caseController;
    @Mock
    CaseService caseService;

    public  final ObjectMapper objectMapper=new ObjectMapper();
    private static MockMvc mockMvc;
    private static Logger logger;
    @BeforeEach
    public  void setUp(){
         cases =new ArrayList<CaseDTO>();
        CaseDTO Case=CaseDTO.builder()
                .case_id(1000)
                .case_status(Resolved)
                .case_document_url("http://tests.com")
                .patient_id(1000)
                .analysis_content("analysis content of the case").build();
        cases.add(Case);

        mockMvc=MockMvcBuilders.standaloneSetup(caseController).build();
        logger = LoggerFactory.getLogger(CasesControllerTest.class);


    }


   @Test
    public void getDoctorCasesTest(){
        //Mocking caseService.getDoctorCases behaviour
        when(caseService.getDoctorCases(1L)).thenReturn(cases);

        //calling the controller
        List<CaseDTO> result=caseController.getDoctorCases();

        //verifying the result
        assertEquals(result,cases);
    }
    @Test
    public void createCaseByDoctorTest() throws Exception {
        CaseRequest caseRequest=CaseRequest.builder()
                .analysis_content("these informations are so sensitive")
                .case_document_url("http://analysis_results")
                .build();
        String requestBody=objectMapper.writeValueAsString(caseRequest);
        //AtomicBoolean isTrue= new AtomicBoolean(false);
        AtomicBoolean istrue = new AtomicBoolean(false);
        doAnswer(element-> {
            //isTrue.set(true);
            istrue.set(true);
            return null;
        }).when(caseService).createCaseByDoctor(1L,"these informations are so sensitive","http://analysis_results");
        mockMvc.perform(MockMvcRequestBuilders
                        .post("http://localhost:8080/case")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk());
        assert(istrue.get());
        }

        @Test
        public void updateCaseByDoctorTest() throws Exception {
            UpdateCase updateCase=UpdateCase.builder()
                    .case_id(100L)
                    .analysis_content("the ananlisis shows that patient is having only some simple dificulties")
                    .case_status(Resolved)
                    .build();
            String updateRequestBody=objectMapper.writeValueAsString(updateCase);
            AtomicBoolean serviceDone=new AtomicBoolean(false);

            doAnswer(element->{
                serviceDone.set(true);
                return null;
            }).when(caseService).updateCaseByDoctor(updateCase,1L);

            mockMvc.perform(MockMvcRequestBuilders.put("http://localhost:8080/case")
                    .content(updateRequestBody)
                    .contentType(MediaType.APPLICATION_JSON)
                    ).andExpect(status().isOk());
            assert(serviceDone.get());
            logger.info("\ntest passed successfully");

        }

        @Test
        public void deleteCaseByDoctorTest() throws Exception {
            //mocking the deleteCaseByDoctor behaviour
            AtomicBoolean methodCalled=new AtomicBoolean(false);
            doAnswer(deleteAction->{
                methodCalled.set(true);
                return null;


            }).when(caseService).deleteCaseByDoctor(1L,1L);
            mockMvc.perform(MockMvcRequestBuilders
                    .delete("http://localhost:8080/case/1")
                    .contentType(MediaType.APPLICATION_JSON)
                    ).andExpect(status().isOk());

            assert(methodCalled.get());



        }
        //commet for having a change
    //it is beeing hard





}

