package com.train.aimforthehead.services;

import com.train.aimforthehead.domain.entities.BookEntity;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<BookEntity> findAll();

    List<BookEntity> findByAuthorID(int id);

    Optional<BookEntity> findOne(int id);

    List<BookEntity> findBooksLike(String title);
}
