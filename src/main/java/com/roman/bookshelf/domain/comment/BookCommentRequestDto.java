package com.roman.bookshelf.domain.comment;

public record BookCommentRequestDto(String login, Long bookId, String comment) {
}
