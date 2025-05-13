package com.example.attendance.controller;

import com.example.attendance.model.Book;
import com.example.attendance.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/books")
    public String listBooks(Model model) {
        List<Book> books = bookService.findAll();
        model.addAttribute("books", books);
        return "book-list";
    }

    @GetMapping("/books/search")
    public String searchBooks(@RequestParam("title") String title, Model model) {
        List<Book> books = bookService.findByTitle(title);
        model.addAttribute("books", books);
        return "book-list";
    }
}