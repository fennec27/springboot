package com.example.demo.repository;

import com.example.demo.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findBooksByTitleOrderByTitleAsc(String title);
    List<Book> findBooksByTitleNotNullAndAuthorsNotEmpty();
    void deleteByTitleContaining(String text);
    void deleteByIdBetween(Long start, Long end);

}
