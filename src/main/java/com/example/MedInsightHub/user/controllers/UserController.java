package com.example.MedInsightHub.user.controllers;

import com.example.MedInsightHub.user.UpdateProfileRequest;
import com.example.MedInsightHub.user.dto.UserDTO;
import com.example.MedInsightHub.user.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "user")
public class UserController {
    private final UserService userService;

    @GetMapping
    public UserDTO getUserInfo(){
        long user_id = 1;
        return userService.getUserInfo(user_id);
    }

    @PutMapping
    public void updateProfile(@RequestBody UpdateProfileRequest updateProfileRequest){
        long user_id = 1;
        userService.updateProfile(updateProfileRequest, user_id);
    }
}
