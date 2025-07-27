package com.libarymanagementsystem.project1.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "Borrows")
public class BookBorrowing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id ;

    @Column(name = "borrower_name",nullable = false)
    private  String borrowerName;

    @Column(name = "borrower_email",nullable = false)
    private  String borrowerEmail;

    @Column(name = "borrowing_date",nullable = false)
    private LocalDate borrowingDate;

    @Column(name = "return_date",nullable = true)
    private LocalDate returnDate;

    // Bir ödünç alma işlemi bir kitaba ait (ManyToOne)
    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

}
