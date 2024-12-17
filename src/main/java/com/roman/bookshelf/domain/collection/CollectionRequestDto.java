package com.roman.bookshelf.domain.collection;

public record CollectionRequestDto(String login, Long bookId, String status) {
}
