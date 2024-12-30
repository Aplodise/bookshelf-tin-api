package com.roman.bookshelf.service.mapper;

import com.roman.bookshelf.domain.author.AuthorMinResponseDto;
import com.roman.bookshelf.domain.author.AuthorRequestDto;
import com.roman.bookshelf.domain.author.AuthorResponseDto;
import com.roman.bookshelf.domain.author.AuthorWithBooksResponseDto;
import com.roman.bookshelf.persistance.model.Author;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    Author toEntity(AuthorRequestDto authorRequestDto);
    AuthorResponseDto toDto(Author author);
    AuthorMinResponseDto toMinDto(Author author);
    @Mapping(source = "books", target = "books")
    AuthorWithBooksResponseDto toDtoWithBooks(Author author);

    default List<AuthorResponseDto> toDtoList(List<Author> authors){
       return authors.
                stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
    default List<AuthorMinResponseDto> toMinDtoList(List<Author> authors){
        return authors.
                stream()
                .map(this::toMinDto)
                .collect(Collectors.toList());
    }
}
