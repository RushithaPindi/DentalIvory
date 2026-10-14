package com.dental.ivory.service.impl;

import com.dental.ivory.exceptions.EmailNotFundException;
import com.dental.ivory.exceptions.UserNotFoundException;
import com.dental.ivory.model.entity.AdminEntity;
import com.dental.ivory.model.request.AdminRequest;
import com.dental.ivory.model.request.LoginRequest;
import com.dental.ivory.model.response.AdminResponse;
import com.dental.ivory.model.response.LoginResponse;
import com.dental.ivory.repository.AdminRepository;
import com.dental.ivory.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public class AdminServiceImpl implements AdminService {
   @Autowired
    AdminRepository adminRepository;

    public AdminResponse adminRegistration(AdminRequest adminRequest) throws UserNotFoundException{
        Optional<AdminEntity> existingAdmin= adminRepository.findByUserName(adminRequest.getUserName());
        if(existingAdmin.isPresent()){
            throw new UserNotFoundException("User already exists, try another username");
        }
        AdminEntity adminEntity = new AdminEntity();
        adminEntity.setUserName( adminRequest.getUserName());
        adminEntity.setPassword(adminRequest.getPassword());
        adminEntity.setEmail(adminRequest.getEmail());
        adminRepository.save(adminEntity);
        AdminResponse adminResponse = new AdminResponse();
        adminResponse.setAdminId(adminEntity.getId());
        adminResponse.setUserName(adminEntity.getUserName());
        adminResponse.setEmail(adminEntity.getEmail());
        return adminResponse;
    }

    public LoginResponse adminLogin(LoginRequest loginRequest) throws EmailNotFundException {
        AdminEntity adminEntity =adminRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(()-> new EmailNotFundException("Admin not found"));
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setEmail(adminEntity.getEmail());
        loginResponse.setAdminId(adminEntity.getId());
        return loginResponse;
    }
}
