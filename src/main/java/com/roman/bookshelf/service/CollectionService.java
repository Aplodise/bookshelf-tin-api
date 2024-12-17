package com.roman.bookshelf.service;


import com.roman.bookshelf.domain.collection.CollectionRequestDto;
import com.roman.bookshelf.domain.collection.CollectionResponseDto;
import com.roman.bookshelf.persistance.model.Book;
import com.roman.bookshelf.persistance.model.BookCollection;
import com.roman.bookshelf.persistance.model.Status;
import com.roman.bookshelf.persistance.model.User;
import com.roman.bookshelf.persistance.repository.BookRepository;
import com.roman.bookshelf.persistance.repository.CollectionRepository;
import com.roman.bookshelf.persistance.repository.UserRepository;
import com.roman.bookshelf.service.mapper.CollectionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CollectionService {
    private final CollectionRepository collectionRepository;
    private final UserRepository userRepository;
    private final CollectionMapper collectionMapper;
    private final BookRepository bookRepository;
    private final static Integer PAGE_SIZE = 15;
    public List<CollectionResponseDto> getCollectionByUserLogin(String login, Integer page){
        User user = userRepository.findByLogin(login).orElseThrow();

        return collectionMapper.toCollectionsDto(collectionRepository.findAllByUserId(user.getId(), PageRequest.of(page, PAGE_SIZE)));
    }

    public CollectionResponseDto addToCollection(CollectionRequestDto collectionRequestDto) {
        User user = userRepository.findByLogin(collectionRequestDto.login()).orElseThrow();
        Book book = bookRepository.findById(collectionRequestDto.bookId()).orElseThrow();
        BookCollection bookCollection = new BookCollection();
        if (collectionRequestDto.status().equals(Status.READ.name()))
            bookCollection.setReadAt(LocalDateTime.now());
        bookCollection.setUser(user);
        bookCollection.setBook(book);
        bookCollection.setStatus(Status.valueOf(collectionRequestDto.status()));
        return collectionMapper.toDto(collectionRepository.save(bookCollection));
    }
}
