package com.example.MedInsightHub.demand;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SendDemandRequest {
    private String demand_message;
    private String demand_document_url;
}
