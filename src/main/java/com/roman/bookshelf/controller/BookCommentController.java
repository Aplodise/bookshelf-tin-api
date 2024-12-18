package com.roman.bookshelf.controller;

import com.roman.bookshelf.domain.comment.BookCommentDto;
import com.roman.bookshelf.domain.comment.BookCommentRequestDto;
import com.roman.bookshelf.service.BookCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class BookCommentController {
    private final BookCommentService bookCommentService;
    @PostMapping
    public ResponseEntity<BookCommentDto> createComment(@RequestBody BookCommentRequestDto bookCommentRequestDto){
        return ResponseEntity.ok(bookCommentService.createComment(bookCommentRequestDto));
    }
}
