package com.example.MedInsightHub.user;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity(name = "_user")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter

public class User implements UserDetails {

    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    private long user_id;
    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private String bio;
    private String email;
    @Enumerated(value = EnumType.STRING)
    private UserType user_type;
    private LocalDateTime registration_date;
    private LocalDateTime last_login_date;
    private String profile_pic_url;
    private int connections_count;
    private boolean online;


    public User(String firstname, String lastname, String username, String password,
                String bio, String email, UserType user_type, LocalDateTime registration_date,
                LocalDateTime last_login_date, String profile_pic_url, int connections_count,
                boolean online) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
        this.bio = bio;
        this.email = email;
        this.user_type = user_type;
        this.registration_date = registration_date;
        this.last_login_date = last_login_date;
        this.profile_pic_url = profile_pic_url;
        this.connections_count = connections_count;
        this.online = online;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        return List.of(new SimpleGrantedAuthority(user_type.name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
