package com.roman.bookshelf.controller;

import com.roman.bookshelf.domain.book.BookDto;
import com.roman.bookshelf.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;
    @GetMapping("/books")
    public List<BookDto> getBooks(){
        return bookService.getBooks();
    }
}
