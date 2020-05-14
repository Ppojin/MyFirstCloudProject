package com.example.myappapiusers.service;

import com.example.myappapiusers.entity.UserCreateDto;
import com.example.myappapiusers.entity.UserReadDto;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface UserService extends UserDetailsService {
    UserReadDto createUser(UserCreateDto userDetails);
}
