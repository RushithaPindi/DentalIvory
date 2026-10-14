package com.dental.ivory.service;

import com.dental.ivory.exceptions.EmailNotFundException;
import com.dental.ivory.exceptions.UserNotFoundException;
import com.dental.ivory.model.request.PatientRequest;
import com.dental.ivory.model.response.PatientResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PatientService {
    PatientResponse addPatient(PatientRequest patientRequest);

    List<PatientResponse> addMultiplePatients(List<PatientRequest> patientRequests) throws EmailNotFundException;

    List<PatientResponse> getAllPatients() throws UserNotFoundException;

    PatientResponse getPatientDetailsById(Integer id) throws UserNotFoundException;

    List<PatientResponse> getPatientDetailsByRange(Integer startValue, Integer endValue) throws UserNotFoundException;

    String deletePatientById(Integer patientId) throws UserNotFoundException;

    PatientResponse updatePatientDeatilsByPatientId(Integer patientId, PatientRequest patientRequest) throws UserNotFoundException;

    PatientResponse partialUpdatePatientDetailsById(Integer patientId, PatientRequest patientRequest) throws UserNotFoundException;
}
