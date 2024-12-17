package com.roman.bookshelf.domain.comment;

import java.time.LocalDate;

/**
 * DTO for {@link com.roman.bookshelf.persistance.model.BookComment}
 */
public record BookCommentDto(Long id, String comment, LocalDate createdAt, CommentUserDto commentUserDto) {
}
