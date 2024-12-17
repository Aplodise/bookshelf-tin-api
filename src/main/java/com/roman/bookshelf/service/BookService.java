package com.roman.bookshelf.service;

import com.roman.bookshelf.domain.book.BookDetailsDto;
import com.roman.bookshelf.domain.book.BookDto;
import com.roman.bookshelf.domain.book.BookRequestDto;
import com.roman.bookshelf.persistance.model.Author;
import com.roman.bookshelf.persistance.model.Book;
import com.roman.bookshelf.persistance.repository.AuthorRepository;
import com.roman.bookshelf.persistance.repository.BookRepository;
import com.roman.bookshelf.service.mapper.BookMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final AuthorRepository authorRepository;
    private static final Integer PAGE_SIZE = 15;
    public List<BookDto> getBooks(Integer page){
        return bookMapper.toDtoList(bookRepository.findAll(PageRequest.of(page, PAGE_SIZE)).getContent());
    }

    public BookDetailsDto getBookWithComments(Long id){
        return bookMapper.toBookDetailsDto(bookRepository.findById(id).orElseThrow());
    }

    public BookDto createBook(BookRequestDto bookRequestDto) {
        Author author = authorRepository.findById(bookRequestDto.authorId()).orElseThrow();
        Book book = bookMapper.toEntityFromBookRequest(bookRequestDto);
        book.setAuthor(author);
        return bookMapper.toDto(bookRepository.save(book));
    }
}
