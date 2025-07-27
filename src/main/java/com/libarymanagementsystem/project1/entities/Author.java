package com.libarymanagementsystem.project1.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "author_id")
    private  int id;

    @Column(name = "author_name",unique = true)
    private String name;

    @Column(name = "author_birtdate",nullable = false)
    private int birthDate;

    @Column(name = "author_country",nullable = false)
    private String country;
    // Bir yazar birden fazla kitaba sahip (OneToMany)
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Book> books = new ArrayList<>();
}
