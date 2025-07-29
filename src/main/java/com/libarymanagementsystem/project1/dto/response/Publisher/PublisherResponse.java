package com.libarymanagementsystem.project1.dto.response.Publisher;

import com.libarymanagementsystem.project1.entities.Book;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublisherResponse {

    private  int id;


    private String name ;


    private int establishmentYear;


    private String address ;


    private List<Integer> booksId = new ArrayList<>();
}

