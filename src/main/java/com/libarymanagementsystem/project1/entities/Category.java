package com.libarymanagementsystem.project1.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Categories")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private  int id;

    @Column(name = "category_name")
    private String name;

    @Column(name = "category_description")
    private String description;
    // Bir kategori birden fazla kitaba sahip (ManyToMany)
    @ManyToMany(mappedBy = "categories") // Book tarafındaki field adı
    @JsonIgnore
    private List<Book> books = new ArrayList<>();
}
