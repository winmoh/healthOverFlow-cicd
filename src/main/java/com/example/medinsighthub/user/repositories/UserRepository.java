package com.example.medinsighthub.user.repositories;

import com.example.medinsighthub.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    @Query("select u from _user u where u.username=?1")
    Optional<User> getUserByUsername(String username);
}
