package com.roman.bookshelf.persistance.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "books")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String genre;
    private String summary;
    @Column(name = "cover_url")
    private String coverUrl;
    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;
}
