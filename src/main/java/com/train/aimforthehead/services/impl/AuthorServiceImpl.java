package com.train.aimforthehead.services.impl;

import com.train.aimforthehead.domain.entities.AuthorEntity;
import com.train.aimforthehead.repositories.AuthorRepository;
import com.train.aimforthehead.services.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.util.stream.Collector;

@Service
public class AuthorServiceImpl implements AuthorService {
    private AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<AuthorEntity> findAll() {
        return StreamSupport.stream(authorRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }
    @Override
    public Optional<AuthorEntity> findOne(int id) {
        return authorRepository.findById(id);
    }
}
