package com.libarymanagementsystem.project1.dto.request.Author;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorSaveRequest {
    @NotNull(message = "Yazar İsmi boş veya null olamaz")

    private String name;

    @NotNull(message = "Doğum yılı boş olamaz")
    private int birthDate;

    @NotNull(message = "Ülke bilgisi boş olamaz")
    private String country;
}
