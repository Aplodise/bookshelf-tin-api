package com.roman.bookshelf.domain.book;

import jakarta.validation.constraints.Size;

public record SummaryDto(@Size(min = 2, max = 2000) String summary) {
}
