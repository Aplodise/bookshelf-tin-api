package com.roman.bookshelf.persistance.repository;

import com.roman.bookshelf.persistance.model.BookComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookCommentRepository extends JpaRepository<BookComment, Long> {
}
