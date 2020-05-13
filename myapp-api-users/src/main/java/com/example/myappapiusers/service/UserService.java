package com.example.myappapiusers.service;

import com.example.myappapiusers.entity.UserCreateDto;
import com.example.myappapiusers.entity.UserReadDto;

public interface UserService {
    UserReadDto createUser(UserCreateDto userDetails);
}
