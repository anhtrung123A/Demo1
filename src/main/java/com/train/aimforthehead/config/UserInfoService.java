package com.train.aimforthehead.config;

import com.train.aimforthehead.domain.entities.UserEntity;
import com.train.aimforthehead.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

@Configuration
public class UserInfoService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> user = userRepository.findByusername(username);
        return user.map(UserInfoDetails::new).orElseThrow(()->new UsernameNotFoundException("User does not exist"));
    }
}
