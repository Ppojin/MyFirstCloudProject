package com.example.myappapiusers.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class UserDto {
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class CreateUser{
        private String firstName;
        private String lastName;
        private String email;

        private String userId;
        private String password;

        public UserEntity toEntity(){
            return UserEntity.builder()
                    .email(this.email)
                    .firstName(this.firstName)
                    .lastName(this.lastName)
                    .userId(this.userId)
                    .encryptedPassword(this.password)
                    .build();
        }
    }
}
