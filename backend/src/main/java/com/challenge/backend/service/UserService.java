
package com.challenge.backend.service;

import com.challenge.backend.dto.RegisterRequest;
import com.challenge.backend.dto.RegisterResponse;
import com.challenge.backend.model.User;

import org.springframework.stereotype.Service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class UserService {

    private final BCryptPasswordEncoder passwordEncoder =
            new BCryptPasswordEncoder();

    private final Map<String, User> users = new HashMap<>();

    public synchronized RegisterResponse register(RegisterRequest request) {

        String normalizedEmail = request.email().trim().toLowerCase();

        if (users.containsKey(normalizedEmail)) {
            throw new IllegalArgumentException("Email already registered");
        }

        String id = UUID.randomUUID().toString();

        User user = new User(
                id,
                request.name(),
                normalizedEmail,
                passwordEncoder.encode(request.password()),
                request.age()
        );

        users.put(normalizedEmail, user);

        return new RegisterResponse(
                user.getId(),
                user.getName(),
                user.getEmail()
        );
    }

    public synchronized java.util.List<RegisterResponse> getAllUsers() {

        return users.values().stream()
                .map(user -> new RegisterResponse(
                        user.getId(),
                        user.getName(),
                        user.getEmail()
                ))
                .toList();
    }
}
