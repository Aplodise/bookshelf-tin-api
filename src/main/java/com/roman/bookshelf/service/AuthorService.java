package com.roman.bookshelf.service;

import com.roman.bookshelf.domain.author.*;
import com.roman.bookshelf.domain.book.SummaryDto;
import com.roman.bookshelf.persistance.model.Author;
import com.roman.bookshelf.persistance.repository.AuthorRepository;
import com.roman.bookshelf.service.mapper.AuthorMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorService {
    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;
    private static final Integer PAGE_SIZE = 15;
    public AuthorsWithPageCountResponseDto getAuthors(Integer page){
        Page<Author> authors = authorRepository.findAll(PageRequest.of(page, PAGE_SIZE));
        return new AuthorsWithPageCountResponseDto(authorMapper.toDtoList(authors.getContent()), authors.getTotalPages());
    }

    public AuthorWithBooksResponseDto getAuthorWithBooks(Long id) {
        return authorMapper.toDtoWithBooks(authorRepository.findById(id).orElseThrow());
    }

    public AuthorResponseDto createAuthor(AuthorRequestDto authorRequestDto) {
        Author author = authorMapper.toEntity(authorRequestDto);
        return authorMapper.toDto(authorRepository.save(author));
    }

    public List<AuthorMinResponseDto> getAuthorsForDropdown() {
        return authorMapper.toMinDtoList(authorRepository.findAll());
    }

    public AuthorWithBooksResponseDto updateAuthorSummary(Long id, SummaryDto summary) {

        Author author = authorRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Author not found."));
        author.setSummary(summary.summary());
        return authorMapper.toDtoWithBooks(authorRepository.save(author));
    }
}
