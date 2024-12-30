package com.roman.bookshelf.persistance.repository;

import com.roman.bookshelf.persistance.model.BookCollection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CollectionRepository extends JpaRepository<BookCollection, Long> {
    Page<BookCollection> findAllByUserId(Long id, Pageable pageable);

    boolean existsByUserIdAndBookId(Long userId, Long bookId);
}
