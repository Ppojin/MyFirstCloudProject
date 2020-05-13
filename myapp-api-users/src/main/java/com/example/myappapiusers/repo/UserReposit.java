package com.example.myappapiusers.repo;

import com.example.myappapiusers.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserReposit extends CrudRepository<UserEntity, Long> {
    UserEntity findByEmail(String email);
}
