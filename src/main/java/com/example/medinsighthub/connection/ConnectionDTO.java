package com.example.medinsighthub.connection;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ConnectionDTO {
    private long connection_id;
    private long user_id;
    private String user_username;
    private String user_firstname;
    private String user_lastname;
    private boolean online;
}
