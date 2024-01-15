package com.example.medinsighthub.cases;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateCase {
    long case_id;
    String analysis_content;
    CaseStatus case_status;
}
