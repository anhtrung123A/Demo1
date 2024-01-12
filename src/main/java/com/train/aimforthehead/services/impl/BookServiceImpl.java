package com.train.aimforthehead.services.impl;

import com.train.aimforthehead.domain.entities.BookEntity;
import com.train.aimforthehead.repositories.BookRepository;
import com.train.aimforthehead.services.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class BookServiceImpl implements BookService {
    private BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<BookEntity> findAll() {
        return StreamSupport.stream(bookRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }
    @Override
    public List<BookEntity> findByAuthorID(int id) {
        return bookRepository.findAllByauthor_id(id);
    }
    @Override
    public Optional<BookEntity> findOne(int id) {
        return bookRepository.findById(id);
    }

    @Override
    public List<BookEntity> findBooksLike(String title) {
        return bookRepository.findBytitleContainingIgnoreCase(title);
    }
}
