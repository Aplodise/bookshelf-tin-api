package com.roman.bookshelf.controller;

import com.roman.bookshelf.domain.author.AuthorRequestDto;
import com.roman.bookshelf.domain.author.AuthorResponseDto;
import com.roman.bookshelf.domain.author.AuthorWithBooksResponseDto;
import com.roman.bookshelf.service.AuthorService;
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
    public ResponseEntity<List<AuthorResponseDto>> getAuthors(@RequestParam(required = false, defaultValue = "0") Integer page){
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

}
