package com.example.attendance.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.example.attendance.model.Book;

public interface BookRepository extends CrudRepository<Book, Long> {
    // Custom query methods can go here if needed
    List<Book> findByTitle(String title);
}
