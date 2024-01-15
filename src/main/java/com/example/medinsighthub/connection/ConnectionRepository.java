package com.example.medinsighthub.connection;

import com.example.medinsighthub.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConnectionRepository extends JpaRepository<Connection, Long> {

    @Query("select c from Connection c where (c.user_sender=?1 or c.user_receiver=?1) and c.connection_status='Accepted'")
    List<Connection> findConnectionsByUser(User _user);

    @Query("select c from Connection c where (c.user_sender=?1 or c.user_receiver=?1) and c.connection_status='Accepted'")
    List<Connection> findPendingConnectionsByPatient(User patient);
}
