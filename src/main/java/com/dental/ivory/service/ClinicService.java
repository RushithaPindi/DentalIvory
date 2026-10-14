package com.dental.ivory.service;

import com.dental.ivory.exceptions.ClinicNotFoundException;
import com.dental.ivory.exceptions.EmailNotFundException;
import com.dental.ivory.exceptions.UserNotFoundException;
import com.dental.ivory.model.request.ClinicRequest;
import com.dental.ivory.model.response.ClinicResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClinicService {
    ClinicResponse addClinic(ClinicRequest clinicRequest) throws ClinicNotFoundException;
    List<ClinicResponse> multipleClinics(List<ClinicRequest> clinicRequest) throws ClinicNotFoundException, EmailNotFundException;

    List<ClinicResponse> getAllClinics();


    ClinicResponse getClinicById(Integer clinicId) throws UserNotFoundException;

    List<ClinicResponse> getClinicDetailsByName(String clinicName) throws UserNotFoundException;

    Long countAllClinicDetails();

    String deleteClinicNames(Integer id);

    ClinicResponse updateClinicDetailsById(Integer id, ClinicRequest clinicRequest) throws UserNotFoundException;
}
