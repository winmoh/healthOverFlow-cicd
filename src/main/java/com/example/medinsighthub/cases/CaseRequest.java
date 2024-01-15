package com.example.medinsighthub.cases;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class CaseRequest {
    private Long patient_id;
    private String analysis_content;
    private String case_document_url;
}
