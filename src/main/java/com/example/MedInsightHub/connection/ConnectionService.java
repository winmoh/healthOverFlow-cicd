package com.example.MedInsightHub.connection;

import com.example.MedInsightHub.user.User;
import com.example.MedInsightHub.user.UserType;
import com.example.MedInsightHub.user.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ConnectionService {
    private final ConnectionRepository connectionRepository;
    private final UserRepository userRepository;
    public List<ConnectionDTO> getConnections(Long user_id) {
        User _user = userRepository.findById(user_id).orElseThrow();
        return connectionRepository.findConnectionsByUser(_user).stream().map(
                connection -> new ConnectionDTO(
                        connection.getConnection_id(),
                        connection.getUser_sender().getUser_id()!=user_id ?
                                connection.getUser_sender().getUser_id() : connection.getUser_receiver().getUser_id(),
                        connection.getUser_sender().getUser_id()!=user_id ?
                                connection.getUser_sender().getUsername() : connection.getUser_receiver().getUsername(),
                        connection.getUser_sender().getUser_id()!=user_id ?
                                connection.getUser_sender().getFirstname() : connection.getUser_receiver().getFirstname(),
                        connection.getUser_sender().getUser_id()!=user_id ?
                                connection.getUser_sender().getLastname() : connection.getUser_receiver().getLastname(),
                        connection.getUser_sender().getUser_id()!=user_id ?
                                connection.getUser_sender().isOnline() : connection.getUser_receiver().isOnline()
                )
        ).collect(Collectors.toList());
    }

    public void connectTo(long sender_id, long receiver_id) {
        // TODO find if the patient(sender) has a connection already
        User receiver = userRepository.findById(receiver_id).orElseThrow(
                () -> new IllegalStateException("user with id "+receiver_id+" not found")
        );
        User sender = userRepository.findById(sender_id).orElseThrow(
                () -> new IllegalStateException("user with id "+sender_id+" not found")
        );

        if(receiver.getUser_type()==UserType.Patient){
            if(receiver.getConnections_count()!=0){
                throw new IllegalStateException("Patient with id "+receiver_id+" already connected to a doctor");
            }
        }
        if(sender.getUser_type()==receiver.getUser_type()){
            throw new IllegalStateException("cannot connect two "+userRepository.findById(sender_id).orElseThrow().getUser_type());
        }
        Connection connection = new Connection();
        connection.setUser_sender(sender);
        connection.setUser_receiver(receiver);
        connection.setDate_sent(LocalDateTime.now());
        connection.setConnection_status(ConnectionStatus.Pending);
        connectionRepository.save(connection);
        // TODO notification New Connection Request
    }

    public void acceptConnection(long connection_id, long user_id) {
        Connection connection = connectionRepository.findById(connection_id).orElseThrow();
        connection.setConnection_status(ConnectionStatus.Accepted);
        if(userRepository.findById(user_id).orElseThrow().getUser_type()== UserType.Patient){
            List<Connection> list_of_pending_connections = connectionRepository.findPendingConnectionsByPatient(userRepository.findById(user_id).orElseThrow());
            for(Connection pending_connection : list_of_pending_connections){
                connectionRepository.delete(pending_connection);
            }
        }
        // TODO notification connection accepted
    }

    public void refuseConnection(long connection_id, long user_id) {
        Optional<Connection> connection = connectionRepository.findById(connection_id);
        if(connection.isEmpty()){
            throw new IllegalStateException("connection with id "+connection_id+" not found!!!");
        }
        long id1 = connection.orElseThrow().getUser_sender().getUser_id();
        long id2 = connection.orElseThrow().getUser_receiver().getUser_id();
        if(id1!=user_id && id2!=user_id){
            throw new IllegalStateException("user with id "+user_id+" does not have permission to remove this specific connection!!!");
        }
        connectionRepository.deleteById(connection_id);
        // TODO notification connection refused
    }
}
