package com.roman.bookshelf.domain.author;

import com.roman.bookshelf.domain.book.BookDto;

import java.time.LocalDate;
import java.util.Set;

public record AuthorWithBooksResponseDto(Long id,
                                         String firstName,
                                         String lastName,
                                         String coverUrl,
                                         String summary,
                                         LocalDate birthDate,
                                         Set<BookDto> books) {
}
