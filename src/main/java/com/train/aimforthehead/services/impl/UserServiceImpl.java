package com.train.aimforthehead.services.impl;

import com.train.aimforthehead.domain.entities.UserEntity;
import com.train.aimforthehead.repositories.UserRepository;
import com.train.aimforthehead.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserEntity getInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();
        UserEntity userEntity = new UserEntity();
        Optional<UserEntity> optionalUserEntity = userRepository.findByusername(username);
        if(optionalUserEntity.isPresent()){
            userEntity = optionalUserEntity.get();
        }
        else {
            return null;
        }
        return userEntity;
    }
}
