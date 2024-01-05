package com.example.MedInsightHub.connection;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "connection")
@RequiredArgsConstructor
public class ConnectionController {
    private final ConnectionService connectionService;

    @GetMapping
    public List<ConnectionDTO> getConnections(){
        long user_id=1;
        return connectionService.getConnections(user_id);
    }

    @PostMapping("/{receiver_id}")
    public void connectTo(@PathVariable( name = "receiver_id") long receiver_id){
        long sender_id = 1;
        connectionService.connectTo(sender_id, receiver_id);
    }

    @PutMapping("/accept/{connection_id}")
    public void acceptConnection(@PathVariable( name = "connection_id") long connection_id){
        long user_id = 1;
        connectionService.acceptConnection(connection_id, user_id);
    }

    @DeleteMapping("/refuse/{connection_id}")
    public void refuseConnection(@PathVariable( name = "connection_id") long connection_id){
        long user_id = 1;
        connectionService.refuseConnection(connection_id, user_id);
    }
}
