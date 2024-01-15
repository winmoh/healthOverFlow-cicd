package com.example.medinsighthub.user.controllers;

import com.example.medinsighthub.user.dto.UserDTO;
import com.example.medinsighthub.user.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
