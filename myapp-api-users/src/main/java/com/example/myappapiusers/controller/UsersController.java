package com.example.myappapiusers.controller;

import com.example.myappapiusers.entity.UserDto;
import com.example.myappapiusers.entity.UserEntity;
import com.example.myappapiusers.repo.UserReposit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UsersController {
    //application.properties 정보 가져오기
    @Autowired private Environment env;
    @Autowired private UserReposit userReposit;

    @GetMapping("/status/check")
    public String status(){
        return String.format("[users-ws] Working on port %s", env.getProperty("local.server.port"));
    }

    //사용자 추가 API
    @PostMapping
    public String createUser(@RequestBody UserDto.CreateUser userDto){
        UserEntity user = userDto.toEntity();
        userReposit.save(user);
        return "SUCCESS";
    }
}
