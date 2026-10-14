package com.dental.ivory.repository;

import com.dental.ivory.model.entity.AdminEntity;


import java.util.Optional;

public interface LoginRepository extends BaseRepository<AdminEntity> {

     Optional<AdminEntity> findByEmail(String email);

}
