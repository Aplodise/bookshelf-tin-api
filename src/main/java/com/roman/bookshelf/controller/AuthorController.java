package com.roman.bookshelf.controller;

import com.roman.bookshelf.domain.author.*;
import com.roman.bookshelf.domain.book.BookDetailsDto;
import com.roman.bookshelf.domain.book.SummaryDto;
import com.roman.bookshelf.service.AuthorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/authors")
public class AuthorController {
    private final AuthorService authorService;

    @GetMapping
    public ResponseEntity<AuthorsWithPageCountResponseDto> getAuthors(@RequestParam(required = false, defaultValue = "0") Integer page){
        return ResponseEntity.ok(authorService.getAuthors(page));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorWithBooksResponseDto> getAuthor(@PathVariable Long id){
        return ResponseEntity.ok(authorService.getAuthorWithBooks(id));
    }

    @PostMapping
    public ResponseEntity<AuthorResponseDto> createAuthor(@RequestBody AuthorRequestDto authorRequestDto){
        return ResponseEntity.ok(authorService.createAuthor(authorRequestDto));
    }

    @GetMapping("/dropdown")
    public ResponseEntity<List<AuthorMinResponseDto>> getAuthorsForDropdown(){
        return ResponseEntity.ok(authorService.getAuthorsForDropdown());
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthorWithBooksResponseDto> updateBookSummary(
            @PathVariable Long id,
            @RequestBody @Valid SummaryDto summary) {
        AuthorWithBooksResponseDto updatedAuthor = authorService.updateAuthorSummary(id, summary);
        return ResponseEntity.ok(updatedAuthor);
    }
}
