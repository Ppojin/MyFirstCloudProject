package com.example.myappaccountservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts")
public class AccountsController {
    //application.properties 정보 가져오기
    @Autowired
    Environment env;

    @GetMapping("/status/check")
    public String status(){
        return String.format("[accounts-ws] Working on port %s", env.getProperty("local.server.port"));
    }
}
