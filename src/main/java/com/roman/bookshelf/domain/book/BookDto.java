package com.roman.bookshelf.domain.book;

import java.io.Serializable;

/**
 * DTO for {@link com.roman.bookshelf.persistance.model.Book}
 */
public record BookDto(Long id, String title, String coverUrl, AuthorNameDto author) implements Serializable {
}