package com.roman.bookshelf.service;


import com.roman.bookshelf.domain.collection.CollectionRequestDto;
import com.roman.bookshelf.domain.collection.CollectionResponseDto;
import com.roman.bookshelf.domain.collection.CollectionStatusDto;
import com.roman.bookshelf.domain.collection.CollectionWithPageCountResponseDto;
import com.roman.bookshelf.persistance.model.Book;
import com.roman.bookshelf.persistance.model.BookCollection;
import com.roman.bookshelf.persistance.model.Status;
import com.roman.bookshelf.persistance.model.User;
import com.roman.bookshelf.persistance.repository.BookRepository;
import com.roman.bookshelf.persistance.repository.CollectionRepository;
import com.roman.bookshelf.persistance.repository.UserRepository;
import com.roman.bookshelf.service.mapper.CollectionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
    public CollectionWithPageCountResponseDto getCollectionByUserLogin(String login, Integer page){
        User user = userRepository.findByLogin(login).orElseThrow();
        Page<BookCollection> userCollection = collectionRepository.findAllByUserId(user.getId(), PageRequest.of(page, PAGE_SIZE));

        return new CollectionWithPageCountResponseDto(collectionMapper.toCollectionsDto(userCollection.getContent()), userCollection.getTotalPages());
    }

    public CollectionResponseDto addToCollection(CollectionRequestDto collectionRequestDto) {
        User user = userRepository.findByLogin(collectionRequestDto.login()).orElseThrow();
        Book book = bookRepository.findById(collectionRequestDto.bookId()).orElseThrow();

        if (collectionRepository.existsByUserIdAndBookId(user.getId(), book.getId())) {
            throw new IllegalStateException("This book is already in your collection.");
        }

        BookCollection bookCollection = new BookCollection();
        if (collectionRequestDto.status().equals(Status.READ.name()))
            bookCollection.setReadAt(LocalDateTime.now());
        else {
            bookCollection.setReadAt(null);
        }
        bookCollection.setUser(user);
        bookCollection.setBook(book);
        bookCollection.setStatus(Status.valueOf(collectionRequestDto.status()));
        return collectionMapper.toDto(collectionRepository.save(bookCollection));
    }

    public void deleteCollection(Long id) {
        BookCollection bookCollection = collectionRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Collection not found!"));
        collectionRepository.delete(bookCollection);
    }

    public CollectionResponseDto updateBookStatus(Long id, CollectionStatusDto newStatus) {
        BookCollection bookCollection = collectionRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Collection not found!"));

        Status currentStatus = bookCollection.getStatus();
        Status updatedStatus;
        try {
            updatedStatus = Status.valueOf(newStatus.status().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid status: " + newStatus);
        }
        if (currentStatus == Status.READ) {
            throw new IllegalArgumentException("Cannot change status from READ to another status.");
        }

        if (currentStatus == Status.IN_PROGRESS && updatedStatus == Status.PLANNED) {
            throw new IllegalArgumentException("Cannot change status from IN_PROGRESS to PLANNED.");
        }

        if (currentStatus == Status.IN_PROGRESS && updatedStatus != Status.READ) {
            throw new IllegalArgumentException("Invalid status transition from IN_PROGRESS.");
        }

        bookCollection.setStatus(updatedStatus);
        if (updatedStatus == Status.READ) {
            bookCollection.setReadAt(LocalDateTime.now());
        } else {
            bookCollection.setReadAt(null);
        }

        return collectionMapper.toDto(collectionRepository.save(bookCollection));
    }
}
