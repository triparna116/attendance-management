package com.example.attendance.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.attendance.model.Book;
import com.example.attendance.service.BookingService;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookingService bookingService;

    @GetMapping
    public Iterable findAll() {
        return bookingService.findAll();
    }

    @GetMapping("/title/{bookTitle}")
    public List findByTitle(@PathVariable String bookTitle) {
        return bookingService.findByTitle(bookTitle);
    }

    @GetMapping("/{id}")
    public Book findOne(@PathVariable Long id){
        return bookingService.findOne(id);
    }

    @PostMapping
    //@ResponseStatus(HttpStatus .CREATED)
    // TODO: How to set ResponseStatus using HttpStatus
    public Book create(@RequestBody Book book) {
        return bookingService.create(book);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        bookingService.delete(id);
    }

    @PutMapping("/{id}")
    public Book updateBook(@RequestBody Book book, @PathVariable Long id) {
        if (book.getId() != id) {
          // throw new BookIdMismatchException();
          // TODO: How to throw exception
        }
        return bookingService.updateBook(book);
    }
}
