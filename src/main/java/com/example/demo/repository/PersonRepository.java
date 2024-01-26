package com.example.demo.repository;

import com.example.demo.entity.Book;
import com.example.demo.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {


    /*
    List<Person> findBooksByTitleOrderByTitleAsc(String title);
    List<Book> findBooksByTitleNotNullAndAuthorsNotEmpty();
    void deleteByTitleContaining(String text);
    void deleteByIdBetween(Long start, Long end);
*/

}
