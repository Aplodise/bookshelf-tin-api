package com.roman.bookshelf.domain.author;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record AuthorRequestDto(@NotEmpty @Size(min = 2, max = 50) String firstName, @NotEmpty @Size(min = 2, max = 50) String lastName,
                               @Past LocalDate birthDate, String coverUrl) {
}
