package com.roman.bookshelf.domain.author;

import java.util.List;

public record AuthorsWithPageCountResponseDto(List<AuthorResponseDto> authors, Integer pageCount) {
}
