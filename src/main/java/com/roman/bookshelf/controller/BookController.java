package com.roman.bookshelf.controller;

import com.roman.bookshelf.domain.book.BookDetailsDto;
import com.roman.bookshelf.domain.book.BookDto;
import com.roman.bookshelf.domain.book.BookRequestDto;
import com.roman.bookshelf.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;
    @GetMapping
    public ResponseEntity<List<BookDto>> getBooks(@RequestParam(required = false, defaultValue = "0") Integer page){
        return ResponseEntity.ok(bookService.getBooks(page));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDetailsDto> getBook(@PathVariable Long id){
        return ResponseEntity.ok(bookService.getBookWithComments(id));
    }

    @PostMapping
    public ResponseEntity<BookDto> createBook(@RequestBody BookRequestDto bookRequestDto){
        return ResponseEntity.ok(bookService.createBook(bookRequestDto));
    }
}
