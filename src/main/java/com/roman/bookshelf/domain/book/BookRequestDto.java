package com.roman.bookshelf.domain.book;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record BookRequestDto(@NotNull Long authorId,
                             @NotEmpty @Size(max = 1000) String summary,
                             String coverUrl,
                             @NotEmpty @Size(max = 50) String genre,
                             @NotEmpty @Size(min = 2, max = 100) String title) {
}
