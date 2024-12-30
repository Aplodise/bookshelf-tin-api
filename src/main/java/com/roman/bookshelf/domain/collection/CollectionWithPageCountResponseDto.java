package com.roman.bookshelf.domain.collection;

import java.util.List;

public record CollectionWithPageCountResponseDto(List<CollectionResponseDto> collection, Integer pageCount) {
}
