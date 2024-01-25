package com.example.demo.controller;

import com.example.demo.entity.Book;
import com.example.demo.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("book")
@AllArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping(value = "", consumes =  "application/json", produces = "application/json")
    public Book create(@RequestBody Book book) {
        return bookService.create(book);
    }

    @GetMapping(value = "{id}", produces = "application/json")
    public Book getOne(@PathVariable Long id) {
        Optional<Book> book = bookService.getOne(id);

        if(book.isPresent()) {
            return book.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "", produces = "application/json")
    public List<Book> getAll() {
        return bookService.getAll();
    }
}
