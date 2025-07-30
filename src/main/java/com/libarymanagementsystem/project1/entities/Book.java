package com.libarymanagementsystem.project1.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "Books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private int id;

    @Column(name = "book_name",unique = true)
    private String name;

    @Column(name = "publication_year",nullable = true)
    private LocalDate publicationYear;

    @Column(name = "book_stock")
    private int stock;
    // Bir kitap bir yazara ait (ManyToOne)
    @ManyToOne
    @JoinColumn(name = "author_id") // foreign key
    @JsonIgnore
    private Author author;

    // Bir kitap bir yayınevine ait (ManyToOne)
    @ManyToOne
    @JoinColumn(name = "publisher_id")
    @JsonIgnore
    private Publisher publisher;

    // Bir kitap birden fazla kategoriye sahip (ManyToMany)
    @ManyToMany
    @JoinTable(
            name = "book_categories",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    @JsonIgnore
    private List<Category> categories = new ArrayList<>();

    // Bir kitap birden fazla ödünç almaya sahip (OneToMany)
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<BookBorrowing> borrowings = new ArrayList<>();
}
