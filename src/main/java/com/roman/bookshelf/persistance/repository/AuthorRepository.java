package com.roman.bookshelf.persistance.repository;

import com.roman.bookshelf.persistance.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
