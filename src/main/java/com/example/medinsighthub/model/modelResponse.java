package com.example.medinsighthub.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class modelResponse {
    private String class_name;
    private String confidence_score;

}
