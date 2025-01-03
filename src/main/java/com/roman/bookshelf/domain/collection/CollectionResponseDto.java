package com.roman.bookshelf.domain.collection;

import com.roman.bookshelf.domain.book.BookDto;

import java.time.LocalDateTime;

public record CollectionResponseDto(Long id, String status, LocalDateTime readDate, BookDto bookDto) {
}
