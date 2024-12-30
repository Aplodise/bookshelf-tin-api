package com.roman.bookshelf.service.mapper;

import com.roman.bookshelf.domain.collection.CollectionResponseDto;
import com.roman.bookshelf.domain.collection.CollectionWithPageCountResponseDto;
import com.roman.bookshelf.persistance.model.BookCollection;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = BookMapper.class)
public interface CollectionMapper {

    @Mappings({
            @Mapping(source = "book", target = "bookDto"),
            @Mapping(source = "readAt", target = "readDate")
    })
    CollectionResponseDto toDto(BookCollection bookCollection);

    default List<CollectionResponseDto> toCollectionsDto(List<BookCollection> bookCollections){
        return bookCollections
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

}
