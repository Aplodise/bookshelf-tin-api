package com.roman.bookshelf.domain.book;

import java.io.Serializable;
import java.util.List;

public record BooksWithPageCountDto(List<BookDto> books, Integer pageCount) implements Serializable {
}
