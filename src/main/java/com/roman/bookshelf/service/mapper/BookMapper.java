package com.roman.bookshelf.service.mapper;

import com.roman.bookshelf.domain.book.BookDto;
import com.roman.bookshelf.persistance.model.Book;
import org.mapstruct.*;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface BookMapper {


    Book toEntity(BookDto bookDto);

    BookDto toDto(Book book);

    default List<BookDto> toDtoList(List<Book> books) {
        return books.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

}