package com.example.MedInsightHub.cases;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CaseRequest {
    private Long patient_id;
    private String analysis_content;
    private String case_document_url;
}
