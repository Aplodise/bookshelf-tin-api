package com.roman.bookshelf.controller;

import com.roman.bookshelf.domain.book.*;
import com.roman.bookshelf.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;
    @GetMapping
    public ResponseEntity<BooksWithPageCountDto> getBooks(@RequestParam(required = false, defaultValue = "0") Integer page){
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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDetailsDto> updateBookSummary(
            @PathVariable Long id,
            @RequestBody @Valid SummaryDto summary) {
        BookDetailsDto updatedBook = bookService.updateBookSummary(id, summary);
        return ResponseEntity.ok(updatedBook);
    }

}
