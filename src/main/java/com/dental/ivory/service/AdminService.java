package com.dental.ivory.service;

import com.dental.ivory.exceptions.EmailNotFundException;
import com.dental.ivory.exceptions.PasswordNotFoundException;
import com.dental.ivory.exceptions.UserNotFoundException;
import com.dental.ivory.model.request.AdminRequest;
import com.dental.ivory.model.request.LoginRequest;
import com.dental.ivory.model.response.AdminResponse;
import com.dental.ivory.model.response.LoginResponse;
import org.springframework.stereotype.Service;

@Service
public interface AdminService {
    AdminResponse adminRegistration(AdminRequest adminRequest) throws UserNotFoundException;
    LoginResponse adminLogin(LoginRequest loginRequest) throws EmailNotFundException;

}
