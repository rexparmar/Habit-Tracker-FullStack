package com.ht.HabitTracker.Controller;

import com.ht.HabitTracker.DTO.LoginRequest;
import com.ht.HabitTracker.Security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthController {
    private final JwtUtil jwtUtil;
    @Autowired
    @Lazy // Delay injection of AuthenticationProvider
    private AuthenticationProvider authenticationProvider;

    public AuthController(@Lazy AuthenticationProvider authenticationProvider, JwtUtil jwtUtil) {
        this.authenticationProvider = authenticationProvider;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        System.out.println("Received username: " + loginRequest.getUsername());
        System.out.println("Received password: " + loginRequest.getPassword());
        try {
            authenticationProvider.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
            );
            String token = jwtUtil.generateToken(loginRequest.getUsername());
            return ResponseEntity.ok("Bearer " + token);
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid username or password");
        }
    }
}