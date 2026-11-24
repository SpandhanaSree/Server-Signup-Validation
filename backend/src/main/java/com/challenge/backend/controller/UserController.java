
package com.challenge.backend.controller;

import com.challenge.backend.dto.RegisterRequest;
import com.challenge.backend.dto.RegisterResponse;
import com.challenge.backend.service.UserService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public RegisterResponse register(
            @Valid @RequestBody RegisterRequest request) {

        return userService.register(request);
    }

    @GetMapping("/users")
    public java.util.List<RegisterResponse> getAllUsers() {
        return userService.getAllUsers();
    }
}
