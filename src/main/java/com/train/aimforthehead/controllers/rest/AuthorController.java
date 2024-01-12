package com.train.aimforthehead.controllers.rest;

import com.train.aimforthehead.domain.dto.AuthorDto;
import com.train.aimforthehead.domain.entities.AuthorEntity;
import com.train.aimforthehead.mappers.Mapper;
import com.train.aimforthehead.mappers.impl.AuthorMapperImpl;
import com.train.aimforthehead.repositories.AuthorRepository;
import com.train.aimforthehead.services.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class AuthorController {
    private AuthorService authorService;
    private Mapper<AuthorEntity, AuthorDto> authorMapper;
    private AuthorRepository authorRepository;

    public AuthorController(AuthorService authorService, AuthorMapperImpl authorMapper, AuthorRepository authorRepository) {
        this.authorService = authorService;
        this.authorMapper = authorMapper;
        this.authorRepository = authorRepository;
    }
    @GetMapping(path = "/authors")
    public ResponseEntity<List<AuthorDto>> listAuthors()
    {
        List<AuthorEntity> authorEntityList = authorService.findAll();
        if (authorEntityList.isEmpty())
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else
        {
            return new ResponseEntity<>(authorEntityList.stream().map(authorMapper::mapTo).collect(Collectors.toList()), HttpStatus.OK);
        }
    }
    @GetMapping(path = "/authors/{id}")
    public ResponseEntity<AuthorDto> findAuthorById(@PathVariable("id") int id)
    {
        Optional<AuthorEntity> authorEntity = authorService.findOne(id);
        return authorEntity.map(authorEntity1 -> {
            AuthorDto authorDto = authorMapper.mapTo(authorEntity1);
            return new ResponseEntity<>(authorDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
