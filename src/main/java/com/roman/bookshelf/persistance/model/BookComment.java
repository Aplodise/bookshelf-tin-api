package com.roman.bookshelf.persistance.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Table(name = "book_comments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String comment;
    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate createdAt;
}
