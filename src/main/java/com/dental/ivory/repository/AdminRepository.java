package com.dental.ivory.repository;

import com.dental.ivory.model.entity.AdminEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository

public interface AdminRepository extends BaseRepository<AdminEntity>{
    Optional<AdminEntity> findByUserName(String userName);

    Optional<AdminEntity> findByEmail(String email);
}
