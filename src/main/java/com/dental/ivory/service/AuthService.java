package com.dental.ivory.service;


import com.dental.ivory.model.entity.ClinicUserEntity;
import com.dental.ivory.model.request.ClinicLoginRequest;
import com.dental.ivory.model.request.ClinicSignUpRequest;
import com.dental.ivory.model.response.ClinicLoginUserResponse;
import com.dental.ivory.model.response.ClinicUserResponse;
import com.dental.ivory.repository.ClinicUserRepository;
import com.dental.ivory.security.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {
    private final ClinicUserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService userDetailsService;
    @Autowired
    public AuthService(CustomUserDetailsService userDetailsService, JwtTokenUtil jwtTokenUtil,
                       AuthenticationManager authenticationManager, ClinicUserRepository userRepository,
                       BCryptPasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Register a new clinic user
    @Transactional
    public ClinicUserResponse registerClinicUser(ClinicSignUpRequest signUpRequest) {
        // Check if a user with the given email already exists
        if (userRepository.findByEmail(signUpRequest.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email is already in use.");
        }
        // Encrypt the password
        String encryptedPassword = passwordEncoder.encode(signUpRequest.getPassword());
        // Create a new ClinicUserEntity
        ClinicUserEntity newUser = new ClinicUserEntity();
        newUser.setEmail(signUpRequest.getEmail());
        newUser.setPassword(encryptedPassword);
        newUser.setFirstName(signUpRequest.getFirstName());
        newUser.setLastName(signUpRequest.getLastName());
        // Save the new user to the database
        userRepository.save(newUser);
        // Prepare the response
        ClinicUserResponse response = new ClinicUserResponse();
        response.setMessage("User registered successfully");

        return response;
    }

    // Authenticate clinic user and generate JWT token
    @Transactional
    public ClinicLoginUserResponse authenticateClinicUser(ClinicLoginRequest clinicLoginRequest) {
        // Step 1: Authenticate the user using the authentication manager
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(clinicLoginRequest.getEmail(), clinicLoginRequest.getPassword())
        );
        // Step 2: Extract user details from authentication
        String email = clinicLoginRequest.getEmail();
        UserDetails userDetails = userDetailsService.loadUserByUsername(email);

        // Step 3: Generate JWT token using the authenticated user details
        String token = jwtTokenUtil.generateToken(userDetails.getUsername());

        // Step 4: Prepare and return the response
        ClinicLoginUserResponse response = new ClinicLoginUserResponse();
        response.setToken(token);
        response.setMessage("Login successful");

        return response;
    }
}
