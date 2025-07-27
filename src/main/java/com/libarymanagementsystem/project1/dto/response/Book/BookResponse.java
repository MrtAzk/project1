package com.libarymanagementsystem.project1.dto.response.Book;

import com.libarymanagementsystem.project1.entities.Author;
import com.libarymanagementsystem.project1.entities.Category;
import com.libarymanagementsystem.project1.entities.Publisher;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookResponse {
    private int id ;
    private String name;
    private int stock;
    private int publisherId;
    private int authorId;
    private List<Integer> categoriesId = new ArrayList<>();
}
