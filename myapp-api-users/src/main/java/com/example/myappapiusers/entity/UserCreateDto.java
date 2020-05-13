package com.example.myappapiusers.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateDto{
    @NotNull
    @Size(min=2)
    private String firstName;
    @NotNull
    @Size(min=2)
    private String lastName;
    @NotNull
    @Email
    private String email;
    @NotNull
    @Size(min=8, max=16)
    private String password;

    public UserEntity toEntity(){
        return new UserEntity().builder()
                .userId(UUID.randomUUID().toString())
                .email(this.email)
                .firstName(this.firstName)
                .lastName(this.lastName)
                .encryptedPassword(this.password)
                .build();
    }
}
