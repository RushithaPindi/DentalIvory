package com.dental.ivory.service;


import com.dental.ivory.model.entity.ClinicUserEntity;
import com.dental.ivory.repository.ClinicUserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final ClinicUserRepository clinicUserRepository;  // Assuming this is your JPA repository

    @Autowired
    public CustomUserDetailsService(ClinicUserRepository clinicUserRepository) {
        this.clinicUserRepository = clinicUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Load the ClinicUserEntity by email
        ClinicUserEntity clinicUser = clinicUserRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));


        // Return a UserDetails object (you may need to customize the roles/authorities depending on your requirements)
        return User.builder()
                .username(clinicUser.getEmail())
                .password(clinicUser.getPassword()) // Ensure the password is hashed and encrypted in your DB
                .roles("USER")  // Assign a role (you can expand this based on your user model)
                .build();
    }
}

