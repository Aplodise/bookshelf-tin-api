package com.roman.bookshelf.domain.book;

import com.roman.bookshelf.domain.comment.BookCommentDto;

import java.util.Set;

/**
 * DTO for {@link com.roman.bookshelf.persistance.model.Book}
 */
public record BookDetailsDto(Long id, String coverUrl, String genre, String summary, String title,  Set<BookCommentDto> comments, AuthorNameDto author) {
}
