package com.roman.bookshelf.persistance.repository;

import com.roman.bookshelf.persistance.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

}
