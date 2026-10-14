package com.dental.ivory.repository;


import com.dental.ivory.model.entity.ClinicUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ClinicUserRepository extends JpaRepository<ClinicUserEntity, Long> {

    Optional<ClinicUserEntity> findByEmail(String email);
}