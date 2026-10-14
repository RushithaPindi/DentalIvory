package com.dental.ivory.service.impl;

import com.dental.ivory.exceptions.ClinicNotFoundException;
import com.dental.ivory.exceptions.EmailNotFundException;
import com.dental.ivory.exceptions.UserNotFoundException;
import com.dental.ivory.model.entity.ClinicEntity;
import com.dental.ivory.model.request.ClinicRequest;
import com.dental.ivory.model.response.ClinicResponse;
import com.dental.ivory.model.response.PatientResponse;
import com.dental.ivory.repository.ClinicRepository;
import com.dental.ivory.service.ClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClinicServiceImp implements ClinicService {
    @Autowired
    ClinicRepository clinicRepository;
    @Override
    public ClinicResponse addClinic(ClinicRequest clinicRequest) throws ClinicNotFoundException {
        Optional<ClinicEntity> existingAdmin=clinicRepository.findByClinicNameAndEmail(clinicRequest.getEmail(),clinicRequest.getClinicName());
        if(existingAdmin.isPresent()){
            throw new ClinicNotFoundException("Email and cinic name already exists- Try another names",clinicRequest.getClinicName(),clinicRequest.getEmail());
        }
        ClinicEntity clinicEntity = new ClinicEntity();
        clinicEntity.setClinicName(clinicRequest.getClinicName());
        clinicEntity.setEmail(clinicRequest.getEmail());
        clinicEntity.setAddress(clinicRequest.getAddress());
        clinicEntity.setPhoneNumber(clinicRequest.getPhoneNumber());
        clinicRepository.save(clinicEntity);
        ClinicResponse clinicResponse = new ClinicResponse();
        clinicResponse.setClinicId(clinicEntity.getId());
        clinicResponse.setClinicName(clinicEntity.getClinicName());
        clinicResponse.setEmail(clinicEntity.getEmail());
        clinicResponse.setAddress(clinicEntity.getAddress());
        clinicResponse.setPhoneNumber(clinicEntity.getPhoneNumber());
        return clinicResponse;

    }

    @Override
    public List<ClinicResponse> multipleClinics(List<ClinicRequest> clinicRequests) throws  EmailNotFundException {
        List<ClinicEntity> clinicEntities = new ArrayList<>();


        for (ClinicRequest clinicRequest : clinicRequests) {
            Optional<ClinicEntity> clinicEntity = clinicRepository.findByClinicName(clinicRequest.getClinicName());
            if(clinicEntity.isPresent()){
                throw new EmailNotFundException("Clinic email already exists");
            }
            ClinicEntity clinicEntity1 = new ClinicEntity();
            clinicEntity1.setClinicName(clinicRequest.getClinicName());
            clinicEntity1.setEmail(clinicRequest.getEmail());
            clinicEntity1.setAddress(clinicRequest.getAddress());
            clinicEntity1.setPhoneNumber(clinicRequest.getPhoneNumber());
            clinicEntities.add(clinicEntity1);
        }
         clinicEntities=clinicRepository.saveAll(clinicEntities);
        List<ClinicResponse> clinicResponses = new ArrayList<ClinicResponse>();
        for(ClinicEntity clinicEntity:clinicEntities){
            ClinicResponse clinicResponse1 = new ClinicResponse() ;
            clinicResponse1.setClinicId(clinicEntity.getId());
            clinicResponse1.setClinicName(clinicEntity.getClinicName());
            clinicResponse1.setEmail(clinicEntity.getEmail());
            clinicResponse1.setAddress(clinicEntity.getAddress());
            clinicResponse1.setPhoneNumber(clinicEntity.getPhoneNumber());
            clinicResponses.add(clinicResponse1);

        }
        return clinicResponses;

    }

    @Override
    public List<ClinicResponse> getAllClinics() {

        List<ClinicEntity> clinicEntities=clinicRepository.findAll();
        List<ClinicResponse> clinicResponses = new ArrayList<>();

        for(ClinicEntity clinicEntity:clinicEntities){
            ClinicResponse clinicResponse = new ClinicResponse();
            clinicResponse.setClinicId(clinicEntity.getId());
            clinicResponse.setClinicName(clinicEntity.getClinicName());
            clinicResponse.setPhoneNumber(clinicEntity.getPhoneNumber());
            clinicResponse.setAddress(clinicEntity.getAddress());
            clinicResponse.setEmail(clinicEntity.getEmail());
            clinicResponses.add(clinicResponse);
        }
        return clinicResponses;
    }

    @Override
    public ClinicResponse getClinicById(Integer clinicId) throws UserNotFoundException {
        ClinicEntity clinicEntity= clinicRepository.findById(clinicId)
                .orElseThrow(()-> new UserNotFoundException("data does not exists"));
        ClinicResponse clinicResponse= new ClinicResponse();
        clinicResponse.setClinicId(clinicEntity.getId());
       clinicResponse.setClinicName(clinicEntity.getClinicName());
       clinicResponse.setPhoneNumber(clinicEntity.getPhoneNumber());
       clinicResponse.setEmail(clinicEntity.getEmail());
       clinicResponse.setAddress(clinicEntity.getAddress());
        return clinicResponse;
    }

    @Override
    public List<ClinicResponse> getClinicDetailsByName(String clinicName) throws UserNotFoundException {
        List<ClinicEntity> clinicEntities=  clinicRepository.findAllByClinicName(clinicName);
        List<ClinicResponse> clinicResponses = new ArrayList<>();
        if(clinicEntities.isEmpty()){
            throw new UserNotFoundException("Name not exists");
        }
        for(ClinicEntity clinicEntity:clinicEntities){
            ClinicResponse clinicResponse= new ClinicResponse();
            clinicResponse.setClinicId(clinicEntity.getId());
            clinicResponse.setClinicName(clinicEntity.getClinicName());
            clinicResponse.setPhoneNumber(clinicEntity.getPhoneNumber());
            clinicResponse.setEmail(clinicEntity.getEmail());
            clinicResponse.setAddress(clinicEntity.getAddress());
            clinicResponses.add(clinicResponse);
        }
        return clinicResponses;
    }

    @Override
    public Long countAllClinicDetails() {
        Long clinicCount= clinicRepository.count();
        return clinicCount;
    }

    @Override
    public String deleteClinicNames(Integer id) {
        if (!clinicRepository.existsById(id)) {
            return "Clinic with ID " + id + " not found";
        }
        clinicRepository.deleteById(id);
        return "clinic "+id+" deleted successfully";
    }

    @Override
    public ClinicResponse updateClinicDetailsById(Integer id, ClinicRequest clinicRequest) throws UserNotFoundException {
        ClinicEntity clinicEntity =clinicRepository.findById(id)
                .orElseThrow(()-> new UserNotFoundException("Clinic Id not exists"));

        clinicEntity.setClinicName(clinicRequest.getClinicName());
        clinicEntity.setEmail(clinicRequest.getEmail());
        clinicEntity.setAddress(clinicRequest.getAddress());
        clinicEntity.setPhoneNumber(clinicRequest.getPhoneNumber());
        clinicRepository.save(clinicEntity);
        ClinicResponse clinicResponse = new ClinicResponse();
        clinicResponse.setClinicId(clinicEntity.getId());
        clinicResponse.setClinicName(clinicEntity.getClinicName());
        clinicResponse.setEmail(clinicEntity.getEmail());
        clinicResponse.setAddress(clinicEntity.getAddress());
        clinicResponse.setPhoneNumber(clinicEntity.getPhoneNumber());
        return clinicResponse;
    }
}
