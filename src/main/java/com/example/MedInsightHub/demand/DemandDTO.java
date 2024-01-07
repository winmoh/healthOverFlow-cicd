package com.example.MedInsightHub.demand;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
public class DemandDTO {
    private long demand_id;
    private long user_id;
    private String firstname;
    private String lastname;
    private String username;
    private String demand_message;
    private boolean include_document;
    private DemandStatus demand_status;
    private LocalDateTime demand_date_sent;
}
