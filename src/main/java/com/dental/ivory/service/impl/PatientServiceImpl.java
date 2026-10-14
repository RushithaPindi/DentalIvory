package com.dental.ivory.service.impl;

import com.dental.ivory.exceptions.EmailNotFundException;
import com.dental.ivory.exceptions.UserNotFoundException;
import com.dental.ivory.model.entity.PatientEntity;
import com.dental.ivory.model.request.PatientRequest;
import com.dental.ivory.model.response.PatientResponse;
import com.dental.ivory.repository.PatientRepository;
import com.dental.ivory.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientService {
    @Autowired
    PatientRepository patientRepository;

    @Override
    public PatientResponse addPatient(PatientRequest patientRequest) {
        PatientEntity addPatientEntity = new PatientEntity();
        addPatientEntity.setPatientName(patientRequest.getPatientName());
        addPatientEntity.setPatientEmailId(patientRequest.getPatientEmailId());
        addPatientEntity.setPatientAge(patientRequest.getPatientAge());
        addPatientEntity.setPatientNumber(patientRequest.getPatientNumber());
        addPatientEntity.setPatientAddress(patientRequest.getPatientAddress());
        patientRepository.save(addPatientEntity);
        PatientResponse patientResponse = new PatientResponse();
        patientResponse.setPatientId(addPatientEntity.getId());
        patientResponse.setPatientName(addPatientEntity.getPatientName());
        patientResponse.setPatientEmailId(addPatientEntity.getPatientEmailId());
        patientResponse.setPatientAge(addPatientEntity.getPatientAge());
        patientResponse.setPatientNumber(addPatientEntity.getPatientNumber());
        patientResponse.setPatientAddress(addPatientEntity.getPatientAddress());
        return patientResponse;
    }

    @Override
    public List<PatientResponse> addMultiplePatients(List<PatientRequest> patientRequests) throws EmailNotFundException {
        List<PatientEntity> patientEntities = new ArrayList<>();
        for (PatientRequest patientRequest : patientRequests) {
            Optional<PatientEntity> patientEntity = patientRepository.findByPatientEmailId(patientRequest.getPatientEmailId());
            if (patientEntity.isPresent()) {
                throw new EmailNotFundException("Email already Exists");
            }

            PatientEntity patientEntity1 = new PatientEntity();
            patientEntity1.setPatientName(patientRequest.getPatientName());
            patientEntity1.setPatientEmailId(patientRequest.getPatientEmailId());
            patientEntity1.setPatientAddress(patientRequest.getPatientAddress());
            patientEntity1.setPatientNumber(patientRequest.getPatientNumber());
            patientEntity1.setPatientAge(patientRequest.getPatientAge());

            patientEntities.add(patientEntity1);
        }
        List<PatientEntity> patientEntity2 = patientRepository.saveAll(patientEntities);
        List<PatientResponse> patientResponses = new ArrayList<PatientResponse>();
        for (PatientEntity patientEntity : patientEntity2) {
            PatientResponse patientResponse = new PatientResponse();
            patientResponse.setPatientId(patientEntity.getId());
            patientResponse.setPatientName(patientEntity.getPatientName());
            patientResponse.setPatientEmailId(patientEntity.getPatientEmailId());
            patientResponse.setPatientAddress(patientEntity.getPatientAddress());
            patientResponse.setPatientAge(patientEntity.getPatientAge());
            patientResponse.setPatientNumber(patientEntity.getPatientNumber());

            patientResponses.add(patientResponse);
        }

        return patientResponses;
    }


    public List<PatientResponse> getAllPatients() throws UserNotFoundException {
        List<PatientResponse> patientResponses = new ArrayList<>();
        List<PatientEntity> patientEntities = patientRepository.findAll();
        if(patientEntities.isEmpty()){
            throw new UserNotFoundException("Data not Found, Check the data");
        }
        for (PatientEntity patientEntity : patientEntities) {
            PatientResponse patientResponse = new PatientResponse();
            patientResponse.setClinicId(patientEntity.getClinicEntity().getId());
            patientResponse.setPatientId(patientEntity.getId());
            patientResponse.setPatientName(patientEntity.getPatientName());
            patientResponse.setPatientEmailId(patientEntity.getPatientEmailId());
            patientResponse.setPatientAge(patientEntity.getPatientAge());
            patientResponse.setPatientAddress(patientEntity.getPatientAddress());
            patientResponse.setPatientNumber(patientEntity.getPatientNumber());
            patientResponses.add(patientResponse);
        }

        return patientResponses;
    }

    public PatientResponse getPatientDetailsById(Integer id) throws UserNotFoundException {
        PatientEntity patientEntity = patientRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User details not found in that id"));
        PatientResponse patientResponse = new PatientResponse();
        patientResponse.setPatientId(patientEntity.getId());
        patientResponse.setPatientName(patientEntity.getPatientName());
        patientResponse.setPatientNumber(patientEntity.getPatientNumber());
        patientResponse.setPatientEmailId(patientEntity.getPatientEmailId());
        patientResponse.setPatientAge(patientEntity.getPatientAge());
        patientResponse.setPatientAddress(patientEntity.getPatientAddress());
        return patientResponse;

    }

    @Override
    public List<PatientResponse> getPatientDetailsByRange(Integer startValue, Integer endValue) throws UserNotFoundException {
        List<PatientResponse> patientResponses = new ArrayList<>();
        List<PatientEntity> patientEntities = patientRepository.findByIdBetween(startValue, endValue);
        if (patientEntities.isEmpty()) {
            throw new UserNotFoundException("Patients not found in between range of ids " + startValue + " " + endValue);
        }
        for (PatientEntity patientEntity : patientEntities) {
            PatientResponse patientResponse = new PatientResponse();
            patientResponse.setPatientId(patientEntity.getId());
            patientResponse.setPatientName(patientEntity.getPatientName());
            patientResponse.setPatientNumber(patientEntity.getPatientNumber());
            patientResponse.setPatientEmailId(patientEntity.getPatientEmailId());
            patientResponse.setPatientAge(patientEntity.getPatientAge());
            patientResponse.setPatientAddress(patientEntity.getPatientAddress());
            patientResponses.add(patientResponse);
        }
        return patientResponses;
    }

    @Override
    public String deletePatientById(Integer patientId) throws UserNotFoundException {
        PatientEntity patientEntity = patientRepository.findById(patientId).orElseThrow(() -> new UserNotFoundException("patient id is not exists"));
        patientRepository.delete(patientEntity);

        return null;
    }

    @Override
    public PatientResponse updatePatientDeatilsByPatientId(Integer patientId, PatientRequest patientRequest) throws UserNotFoundException {
        PatientEntity patientEntity = patientRepository.findById(patientId).orElseThrow(() -> new UserNotFoundException("PatientId is not exists"));
        PatientResponse patientResponse = new PatientResponse();
        patientEntity.setPatientName(patientRequest.getPatientName());
        patientEntity.setPatientNumber(patientRequest.getPatientNumber());
        patientEntity.setPatientAge(patientRequest.getPatientAge());
        patientEntity.setPatientEmailId(patientRequest.getPatientEmailId());
        patientEntity.setPatientAddress(patientRequest.getPatientAddress());
        patientRepository.save(patientEntity);
        patientResponse.setPatientId(patientEntity.getId());
        patientResponse.setPatientName(patientEntity.getPatientName());
        patientResponse.setPatientNumber(patientEntity.getPatientNumber());
        patientResponse.setPatientEmailId(patientEntity.getPatientEmailId());
        patientResponse.setPatientAge(patientEntity.getPatientAge());
        patientResponse.setPatientAddress(patientEntity.getPatientAddress());
        return patientResponse;
    }

    @Override
    public PatientResponse partialUpdatePatientDetailsById(Integer patientId, PatientRequest patientRequest) throws UserNotFoundException {
        PatientEntity patientEntity=patientRepository.findById(patientId)
                .orElseThrow(()->new UserNotFoundException("PatientId is not exists"));
        PatientResponse patientResponse = new PatientResponse();
        if (patientRequest.getPatientName() != null) {
            patientEntity.setPatientName(patientRequest.getPatientName());
        }

        if (patientRequest.getPatientAge() != null) {
            patientEntity.setPatientAge(patientRequest.getPatientAge());
        }

        if (patientRequest.getPatientEmailId() != null) {
            patientEntity.setPatientEmailId(patientRequest.getPatientEmailId());
        }

        if (patientRequest.getPatientNumber() != null) {
            patientEntity.setPatientNumber(patientRequest.getPatientNumber());
        }

        if (patientRequest.getPatientAddress() != null) {
            patientEntity.setPatientAddress(patientRequest.getPatientAddress());
        }
        patientRepository.save(patientEntity);
        patientResponse.setPatientId(patientEntity.getId());
        patientResponse.setPatientName(patientEntity.getPatientName());
        patientResponse.setPatientNumber(patientEntity.getPatientNumber());
        patientResponse.setPatientEmailId(patientEntity.getPatientEmailId());
        patientResponse.setPatientAge(patientEntity.getPatientAge());
        patientResponse.setPatientAddress(patientEntity.getPatientAddress());
        return patientResponse;
    }


}