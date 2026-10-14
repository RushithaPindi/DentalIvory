package com.dental.ivory.controller;

import com.dental.ivory.model.request.ClinicLoginRequest;
import com.dental.ivory.model.request.ClinicSignUpRequest;
import com.dental.ivory.model.response.ClinicLoginUserResponse;
import com.dental.ivory.model.response.ClinicUserResponse;
import com.dental.ivory.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<ClinicUserResponse> signUp(@RequestBody ClinicSignUpRequest signUpRequest) {
        ClinicUserResponse response = authService.registerClinicUser(signUpRequest);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<ClinicLoginUserResponse> login(@RequestBody ClinicLoginRequest clinicLoginRequest) {
        ClinicLoginUserResponse response = authService.authenticateClinicUser(clinicLoginRequest);
        // Return the response with JWT token in the Authorization header
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, "Bearer " + response.getToken());

        return ResponseEntity.ok().headers(headers).body(response);
    }
}

