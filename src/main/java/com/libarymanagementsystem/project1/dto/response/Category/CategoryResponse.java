package com.libarymanagementsystem.project1.dto.response.Category;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryResponse {

    private String name;
    private String description;
    private List<Integer> booksId = new ArrayList<>();

}
