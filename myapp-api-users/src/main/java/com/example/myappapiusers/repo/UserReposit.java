package com.example.myappapiusers.repo;

import com.example.myappapiusers.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserReposit extends JpaRepository<UserEntity, Long> {
}
