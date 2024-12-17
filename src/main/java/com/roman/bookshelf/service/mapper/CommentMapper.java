package com.roman.bookshelf.service.mapper;

import com.roman.bookshelf.domain.comment.BookCommentDto;
import com.roman.bookshelf.persistance.model.BookComment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface CommentMapper{

    @Mappings({
            @Mapping(source = "user.firstName", target = "commentUserDto.firstName"),
            @Mapping(source = "user.lastName", target = "commentUserDto.lastName")
    })
    BookCommentDto toDto(BookComment bookComment);


}
