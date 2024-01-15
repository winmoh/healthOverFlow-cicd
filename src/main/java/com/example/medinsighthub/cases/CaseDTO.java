package com.example.medinsighthub.cases;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
@EqualsAndHashCode

public class CaseDTO {
    private long case_id;
    private long patient_id;
    private String analysis_content;
    private String case_document_url;
    private CaseStatus case_status;

}
