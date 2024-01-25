package com.example.demo.controller;

import com.example.demo.entity.Author;
import com.example.demo.service.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("author")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping(value = "", consumes =  "application/json", produces = "application/json")
    public Author create(@RequestBody Author author) {
        return authorService.create(author);
    }

    @GetMapping(value = "{id}", produces = "application/json")
    public Author getOne(@PathVariable Long id) {
        Optional<Author> author = authorService.getOne(id);

        if(author.isPresent()) {
            return author.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

  @DeleteMapping(value = "{id}", produces = "application/json")
  public ResponseEntity<Void> destroy(@PathVariable Long id) {
        Boolean result = authorService.destroy(id);

        if(result) {
            return ResponseEntity.noContent().build();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "", produces = "application/json")
    public List<Author> getAll() {
        return authorService.getAll();
    }
}
