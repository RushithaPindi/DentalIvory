package com.dental.ivory.repository;

import com.dental.ivory.model.entity.PatientEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends BaseRepository <PatientEntity>{
    Optional<PatientEntity> findByPatientEmailId(String patientEmailId);

    List<PatientEntity> findByIdBetween(Integer startValue, Integer endValue);

}
