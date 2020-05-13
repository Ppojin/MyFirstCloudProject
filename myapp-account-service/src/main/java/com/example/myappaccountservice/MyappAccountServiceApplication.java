package com.example.myappaccountservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MyappAccountServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyappAccountServiceApplication.class, args);
    }

}
