package com.libarymanagementsystem.project1.dto.request.Category;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryUpdateRequest {

    @Positive(message = "ID DEĞERİ POZİTİF OLMAK ZORUNDA ")
    private int id;

    @NotNull(message = "Kategori ismi boş olamaz")
    private String name;

    @NotNull(message = "Kategori açıklaması boş olamaz")
    private String description;

    private List<Integer> booksId = new ArrayList<>();
}
