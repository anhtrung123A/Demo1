package com.train.aimforthehead.services;

import com.train.aimforthehead.domain.entities.AuthorEntity;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    List<AuthorEntity> findAll();

    Optional<AuthorEntity> findOne(int id);
}
