package com.roman.bookshelf.service;

import com.roman.bookshelf.domain.book.BookDto;
import com.roman.bookshelf.persistance.repository.BookRepository;
import com.roman.bookshelf.service.mapper.BookMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    public List<BookDto> getBooks(){
        return bookMapper.toDtoList(bookRepository.findAll());
    }
}
