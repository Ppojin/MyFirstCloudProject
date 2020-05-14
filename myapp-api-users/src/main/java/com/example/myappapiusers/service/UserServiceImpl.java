package com.example.myappapiusers.service;

import com.example.myappapiusers.entity.UserCreateDto;
import com.example.myappapiusers.entity.UserEntity;
import com.example.myappapiusers.entity.UserReadDto;
import com.example.myappapiusers.repo.UserReposit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service("UserService")
public class UserServiceImpl implements UserService{
    @Autowired private UserReposit userReposit;
    @Autowired private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserReadDto createUser(UserCreateDto userDetails) {
        //===============================================================//
        //ModelMapper modelMapper = new ModelMapper();
        //modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        //UserEntity userEntity = modelMapper.map(userDetails, UserEntity.class);
        //---------------------------------------------------------------//
        UserEntity userEntity = userDetails.toEntity();
        //===============================================================//
        String cryptedPwd = bCryptPasswordEncoder.encode(userDetails.getPassword());
        userEntity.setEncryptedPassword(cryptedPwd);
        userReposit.save(userEntity);

        return UserReadDto.builder()
                .userId(userEntity.getUserId())
                .email(userEntity.getEmail())
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .build();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = userReposit.findByEmail(email);
        if(userEntity == null){
            throw new UsernameNotFoundException(email);
        }
        return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(),
                true, true, true, true,
                new ArrayList<>());
    }
}
