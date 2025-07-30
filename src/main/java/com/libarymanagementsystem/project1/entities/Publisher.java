package com.libarymanagementsystem.project1.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Publishers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "publisher_id")
    private  int id;

    @Column(name = "publisher_name",nullable = false)
    private String name ;

    @Column(name ="publisher_establishmentYear",nullable = false )
    private int establishmentYear;

    @Column(name = "publisher_address",nullable = false)
    private String address ;

    // Bir yayınevinin birden fazla kitabı olabilir (OneToMany)

    @OneToMany(mappedBy = "publisher", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Book> books = new ArrayList<>();
}
