package com.train.aimforthehead.repositories;

import com.train.aimforthehead.domain.entities.UserEntity;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    Optional<UserEntity> findByusername(String username);
    Optional<UserEntity> findByemail(String email);
}
