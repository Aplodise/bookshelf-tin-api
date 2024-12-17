package com.roman.bookshelf.persistance.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Set;

@Table(name = "users")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name")
    @Size(min = 2, max = 50)
    private String firstName;
    @Column(name = "last_name")
    @Size(min = 2, max = 50)
    private String lastName;
    @Column(nullable = false)
    private String login;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user")
    private Set<BookComment> bookComments;

    @OneToMany(mappedBy = "user")
    private Set<BookCollection> bookCollections;
}
