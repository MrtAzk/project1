package com.libarymanagementsystem.project1.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "Borrows")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookBorrowing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id ;

    @Column(name = "borrower_name",nullable = false)
    private  String borrowerName;

    @Column(name = "borrower_email",nullable = false)
    private  String borrowerEmail;

    @Column(name = "borrowing_date")
    private LocalDate borrowingDate;

    @Column(name = "return_date")
    private LocalDate returnDate;

    // Bir ödünç alma işlemi bir kitaba ait (ManyToOne)
    @ManyToOne
    @JoinColumn(name = "book_id")
    @JsonIgnore
    private Book book;

}
