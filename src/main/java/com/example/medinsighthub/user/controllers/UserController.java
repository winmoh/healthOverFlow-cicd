package com.example.medinsighthub.user.controllers;

<<<<<<< HEAD:src/main/java/com/example/medinsighthub/user/controllers/UserController.java
import com.example.medinsighthub.user.dto.UserDTO;
import com.example.medinsighthub.user.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
=======
import com.example.MedInsightHub.user.dto.JwtResponse;
import com.example.MedInsightHub.user.requests.AuthenticationRequest;
import com.example.MedInsightHub.user.requests.NewUserRequest;
import com.example.MedInsightHub.user.requests.UpdateProfileRequest;
import com.example.MedInsightHub.user.dto.UserDTO;
import com.example.MedInsightHub.user.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
>>>>>>> 3c2e4af78cb2a6bc3188f9034532f151b09d0b0a:src/main/java/com/example/MedInsightHub/user/controllers/UserController.java

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "user")
public class UserController {
    @Autowired
    private final UserService userService;

    @GetMapping
    public UserDTO getUserInfo(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if ( principal instanceof UserDetails){
            long user_id = userService.usernameToUserId(((UserDetails)principal).getUsername());
            return userService.getUserInfo(user_id);
        }
        throw new UsernameNotFoundException("user not found!!!");
    }

    @PostMapping(path = "create")
    public ResponseEntity<JwtResponse> newUser(@RequestBody NewUserRequest newUserRequest){
        return userService.newUser(newUserRequest);
    }

    @PostMapping(path = "authenticate")
    public ResponseEntity<JwtResponse> auth(
            @RequestBody AuthenticationRequest authenticationRequest
    ){
        return userService.auth(authenticationRequest);
    }

    @PutMapping
    public void updateProfile(@RequestBody(required = false) UpdateProfileRequest updateProfileRequest){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if ( principal instanceof UserDetails) {
            long user_id = userService.usernameToUserId(((UserDetails) principal).getUsername());
            userService.updateProfile(updateProfileRequest, user_id);
        } else {
            throw new UsernameNotFoundException("user not found!!!");
        }

    }
}
