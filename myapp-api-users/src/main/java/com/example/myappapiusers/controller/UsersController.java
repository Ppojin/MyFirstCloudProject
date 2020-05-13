package com.example.myappapiusers.controller;

import com.example.myappapiusers.entity.UserCreateDto;
import com.example.myappapiusers.entity.UserReadDto;
import com.example.myappapiusers.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UsersController {
    //application.properties 정보 가져오기
    @Autowired private Environment env;
    @Autowired private UserService userService;

    @GetMapping("/status/check")
    public String status(){
        return String.format("[users-ws] Working on port %s", env.getProperty("local.server.port"));
    }

    //사용자 추가 API
    @PostMapping
    public ResponseEntity<UserReadDto> createUser(@Valid @RequestBody UserCreateDto userDto){
        UserReadDto userReadDto = userService.createUser(userDto);
        ResponseEntity<UserReadDto> body = ResponseEntity.status(HttpStatus.CREATED).body(userReadDto);
        return body;
    }
}
