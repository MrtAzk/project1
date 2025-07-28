package com.libarymanagementsystem.project1.dto.request.Category;

import com.libarymanagementsystem.project1.entities.Book;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

public class CategorySaveRequest {

    @NotNull(message = "Kategori ismi boş olamaz")
    private String name;

    @NotNull(message = "Kategori açıklaması boş olamaz")
    private String description;

    private List<Integer> booksId = new ArrayList<>();
}
