package com.chatapp.controller;

import com.chatapp.entity.User;
import com.chatapp.repository.UserRepository;
import com.chatapp.security.JwtUtil;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthController(UserRepository userRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @PostMapping("/register")
    public String register(@RequestBody User user) {

        // Encrypt password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(user);

        return "User Registered Successfully";
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {

        User dbUser = userRepository.findByEmail(user.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Check password
        if (!passwordEncoder.matches(user.getPassword(), dbUser.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        // Generate JWT
        return jwtUtil.generateToken(dbUser.getEmail());
    }
}