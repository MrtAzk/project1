package com.libarymanagementsystem.project1.dto.request.Author;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorUpdateRequest {
    @Positive(message = "ID DEĞERİ POZİTİF OLMAK ZORUNDA ")
    private int id;
    @NotNull(message = "İSİM BOŞ VEYA NULL OLAMAZ ")
    private String name;
}
