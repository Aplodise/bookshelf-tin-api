package com.roman.bookshelf.service;

import com.roman.bookshelf.domain.book.*;
import com.roman.bookshelf.persistance.model.Author;
import com.roman.bookshelf.persistance.model.Book;
import com.roman.bookshelf.persistance.repository.AuthorRepository;
import com.roman.bookshelf.persistance.repository.BookRepository;
import com.roman.bookshelf.service.mapper.BookMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final AuthorRepository authorRepository;
    private static final Integer PAGE_SIZE = 15;
    public BooksWithPageCountDto getBooks(Integer page){
        Page<Book> pageResult = bookRepository.findAll(PageRequest.of(page, PAGE_SIZE));

        return new BooksWithPageCountDto(bookMapper.toDtoList(pageResult.getContent()), pageResult.getTotalPages());
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

    public void deleteBook(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Book not found with id: " + id));
        bookRepository.delete(book);
    }


    public BookDetailsDto updateBookSummary(Long id, SummaryDto summary) {

        Book book = bookRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Book not found"));
        book.setSummary(summary.summary());
        bookRepository.save(book);

        return bookMapper.toBookDetailsDto(book);
    }
}
