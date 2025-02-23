package com.weatherapp.dashboard.controller;

import com.weatherapp.dashboard.dto.UserRequest;
import com.weatherapp.dashboard.entity.User;
import com.weatherapp.dashboard.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AuthController {
    private UserService userService;

    @PostMapping("/register")
    public User register(@RequestBody UserRequest userRequest){
        return userService.register(userRequest);
    }
    @PostMapping("/login")
    public String login(@RequestBody User user) {

        return userService.verify(user);
    }

}
