package com.dental.ivory.repository;

import com.dental.ivory.model.entity.ClinicEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClinicRepository extends BaseRepository<ClinicEntity> {
    Optional<ClinicEntity> findByClinicNameAndEmail(String clinicName, String email);

    Optional<ClinicEntity> findByClinicName(String clinicName);
    List<ClinicEntity> findAllByClinicName(String clinicName);
}
