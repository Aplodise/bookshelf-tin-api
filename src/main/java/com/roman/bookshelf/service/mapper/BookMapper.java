package com.roman.bookshelf.service.mapper;

import com.roman.bookshelf.domain.book.BookDetailsDto;
import com.roman.bookshelf.domain.book.BookDto;
import com.roman.bookshelf.domain.book.BookRequestDto;
import com.roman.bookshelf.domain.comment.BookCommentDto;
import com.roman.bookshelf.domain.comment.CommentUserDto;
import com.roman.bookshelf.persistance.model.Book;
import com.roman.bookshelf.persistance.model.BookComment;
import org.mapstruct.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = CommentMapper.class)
public interface BookMapper {


    Book toEntity(BookDto bookDto);

    BookDto toDto(Book book);

    Book toEntityFromBookRequest(BookRequestDto bookRequestDto);
    @Mappings({
            @Mapping(target = "comments", source = "bookComments"),
            @Mapping(target = "author", source = "author")
    })
    BookDetailsDto toBookDetailsDto(Book book);

    default Set<BookCommentDto> mapComments(Set<BookComment> comments) {
        return comments.stream()
                .map(comment -> new BookCommentDto(
                        comment.getId(),
                        comment.getComment(),
                        comment.getCreatedAt(),
                        new CommentUserDto(comment.getUser().getFirstName(),
                                comment.getUser().getLastName())
                        ))
                .collect(Collectors.toSet());
    }
    default List<BookDto> toDtoList(List<Book> books) {
        return books.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

}