package com.roman.bookshelf.service;

import com.roman.bookshelf.domain.author.AuthorRequestDto;
import com.roman.bookshelf.domain.author.AuthorResponseDto;
import com.roman.bookshelf.domain.author.AuthorWithBooksResponseDto;
import com.roman.bookshelf.persistance.model.Author;
import com.roman.bookshelf.persistance.repository.AuthorRepository;
import com.roman.bookshelf.service.mapper.AuthorMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorService {
    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;
    private static final Integer PAGE_SIZE = 15;
    public List<AuthorResponseDto> getAuthors(Integer page){
       return authorMapper.toDtoList(authorRepository.findAll(PageRequest.of(page, PAGE_SIZE)).getContent());
    }

    public AuthorWithBooksResponseDto getAuthorWithBooks(Long id) {
        return authorMapper.toDtoWithBooks(authorRepository.findById(id).orElseThrow());
    }

    public AuthorResponseDto createAuthor(AuthorRequestDto authorRequestDto) {
        Author author = authorMapper.toEntity(authorRequestDto);
        return authorMapper.toDto(authorRepository.save(author));
    }
}
