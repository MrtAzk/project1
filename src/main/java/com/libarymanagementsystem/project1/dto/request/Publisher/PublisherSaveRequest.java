package com.libarymanagementsystem.project1.dto.request.Publisher;

import com.libarymanagementsystem.project1.entities.Book;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PublisherSaveRequest {
    @NotNull(message = "Publisher İsmi Boş olamaz ")
    private String name ;
    @NotNull(message = "Publisher kuruluş yılı giriniz")
    private int establishmentYear;

    private String address ;

    private List<Integer> booksId = new ArrayList<>();
}
