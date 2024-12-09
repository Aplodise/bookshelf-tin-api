package com.roman.bookshelf.domain.book;

import java.io.Serializable;

/**
 * DTO for {@link com.roman.bookshelf.persistance.model.Author}
 */
public record AuthorNameDto(String firstName, String lastName) implements Serializable {
}