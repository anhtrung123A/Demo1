package com.train.aimforthehead.controllers.rest;

import com.train.aimforthehead.domain.dto.BookDto;
import com.train.aimforthehead.domain.entities.BookEntity;
import com.train.aimforthehead.mappers.Mapper;
import com.train.aimforthehead.repositories.BookRepository;
import com.train.aimforthehead.services.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class BookController {
    private Mapper<BookEntity, BookDto> bookMapper;
    private BookService bookService;
    private BookRepository bookRepository;

    public BookController(Mapper<BookEntity, BookDto> bookMapper, BookService bookService, BookRepository bookRepository) {
        this.bookMapper = bookMapper;
        this.bookService = bookService;
        this.bookRepository = bookRepository;
    }

    @CrossOrigin
    @GetMapping(path = "/books")
    public List<BookDto> listBooks(){
        List<BookEntity> bookEntityList = bookService.findAll();
        return bookEntityList.stream().map(bookMapper::mapTo).collect(Collectors.toList());
    }
    @CrossOrigin
    @GetMapping(path = "/books/authors/{author_id}")
    public List<BookDto> findBookByAuthorID(@PathVariable("author_id") int id)
    {
        List<BookEntity> bookEntityList = bookService.findByAuthorID(id);
        return bookEntityList.stream().map(bookMapper::mapTo).collect(Collectors.toList());
    }
    @CrossOrigin
    @GetMapping(path = "books/{book_id}")
    public ResponseEntity<BookDto> findBookByBookID(@PathVariable("book_id") int id)
    {
        Optional<BookEntity> bookEntity = bookService.findOne(id);
        return bookEntity.map(bookEntity1 -> {
            BookDto bookDto = bookMapper.mapTo(bookEntity1);
            return new ResponseEntity<>(bookDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping(path = "books/contain/{title}")
    public ResponseEntity<List<BookDto>> findBookLikes(@PathVariable("title") String title)
    {
        List<BookEntity> bookEntityList = bookService.findBooksLike(title);
        if(bookEntityList.isEmpty())
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else
        {
            return new ResponseEntity<>(bookEntityList.stream().map(bookMapper::mapTo).collect(Collectors.toList()), HttpStatus.OK);
        }
    }
}
