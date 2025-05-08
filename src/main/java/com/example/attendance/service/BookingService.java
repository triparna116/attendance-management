package com.example.attendance.service;

import java.util.List;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.attendance.model.Book;
import com.example.attendance.repository.BookRepository;

@Service
public class BookingService {
    @Autowired
    private BookRepository bookRepository;

    public Iterable findAll() {
        return bookRepository.findAll();
    }

    public List findByTitle(String bookTitle) {
        return bookRepository.findByTitle(bookTitle);
    }

    public Book findOne(Long id) {
        return bookRepository.findById(id).get();
    }

    public Book create(Book book) {
        return bookRepository.save(book);
    }

    public void delete(Long id) {
        bookRepository.deleteById(id);
    }

    public Book updateBook(Book book) {
        return bookRepository.save(book);
    }

    // public Book login(String username, String password) {
    //     User user = userRepository.findByUserName(username);
    //     if (user == null) {
    //         return new InvalidUserNameException();
    //     } else if(!user.getPassword().equals(password)) {
    //         return new InvalidPasswordException();
    //     }
    // }
}
