package com.weatherapp.dashboard.controller;

import com.weatherapp.dashboard.dto.UserResponse;
import com.weatherapp.dashboard.entity.User;
import com.weatherapp.dashboard.service.JWTService;
import com.weatherapp.dashboard.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final JWTService jwtService;
    @GetMapping("/info")
    public ResponseEntity<UserResponse> getUserInfo(@RequestHeader("Authorization") String token) {
        String username = jwtService.extractUserName(token.substring(7));
        User user = userService.findByUsername(username);
        return ResponseEntity.ok(new UserResponse(user.getId(), user.getUsername()));
    }
}
