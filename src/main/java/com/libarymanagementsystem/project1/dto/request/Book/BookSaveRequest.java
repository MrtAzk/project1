package com.libarymanagementsystem.project1.dto.request.Book;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookSaveRequest {

    @NotBlank(message = "Kitap adı boş olamaz")
    private String name;

    @NotNull(message = "Yayın yılı boş olamaz")
    private LocalDate publicationYear; // veya Integer year

    @NotNull
    @Min(value = 0, message = "Stok negatif olamaz")
    private Integer stock;

    @NotNull(message = "Publisher id boş olamaz")
    private Integer publisherId;

    @NotNull(message = "Author id boş olamaz")
    private Integer authorId;

    private List<Integer> categoriesId = new ArrayList<>();
}

