package com.example.MedInsightHub.cases;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CaseDTO {
    private long case_id;
    private long patient_id;
    private String analysis_content;
    private String case_document_url;
    private CaseStatus case_status;
}
