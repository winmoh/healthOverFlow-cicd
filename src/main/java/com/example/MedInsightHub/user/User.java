package com.example.MedInsightHub.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity(name = "_user")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {
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
    private Date registration_date;
    private Date last_login_date;
    private String profile_pic_url;
    private int connections_count;


    public User(String firstname, String lastname, String username, String password,
                String bio, String email, UserType user_type, Date registration_date,
                Date last_login_date, String profile_pic_url, int connections_count) {
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
    }
}
