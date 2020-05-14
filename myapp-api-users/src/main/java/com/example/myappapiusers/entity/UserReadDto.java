package com.example.myappapiusers.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserReadDto {
    private String firstName;
    private String lastName;
    private String email;
    private String userId;
}