package com.example.MedInsightHub.demand;

import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
public class ConsultDemandsDTO {
    private long demand_id;
    private String demand_message;
    private boolean include_document;
    private DemandStatus demand_status;
    private LocalDateTime demand_date_sent;
}
