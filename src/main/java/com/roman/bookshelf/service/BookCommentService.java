package com.roman.bookshelf.service;

import com.roman.bookshelf.domain.comment.BookCommentDto;
import com.roman.bookshelf.domain.comment.BookCommentRequestDto;
import com.roman.bookshelf.persistance.model.Book;
import com.roman.bookshelf.persistance.model.BookComment;
import com.roman.bookshelf.persistance.model.User;
import com.roman.bookshelf.persistance.repository.BookCommentRepository;
import com.roman.bookshelf.persistance.repository.BookRepository;
import com.roman.bookshelf.persistance.repository.UserRepository;
import com.roman.bookshelf.service.mapper.CommentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class BookCommentService {
    private final BookCommentRepository bookCommentRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final CommentMapper commentMapper;
    public BookCommentDto createComment(BookCommentRequestDto bookCommentRequestDto){
        User user = userRepository.findByLogin(bookCommentRequestDto.login()).orElseThrow();
        Book book = bookRepository.findById(bookCommentRequestDto.bookId()).orElseThrow();
        BookComment bookComment = new BookComment();
        bookComment.setComment(bookCommentRequestDto.comment());
        bookComment.setCreatedAt(LocalDate.now());
        bookComment.setBook(book);
        bookComment.setUser(user);
        return commentMapper.toDto(bookCommentRepository.save(bookComment));
    }
}
