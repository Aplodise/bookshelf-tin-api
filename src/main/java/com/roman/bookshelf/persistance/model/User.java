package com.roman.bookshelf.persistance.model;


import jakarta.persistence.*;
import lombok.*;

@Table(name = "users")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "first_name")
    private String lastName;
    private String login;
    private char[] password;

}
