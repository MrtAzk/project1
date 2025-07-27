package com.libarymanagementsystem.project1.business.abstracts;

import com.libarymanagementsystem.project1.entities.Author;
import com.libarymanagementsystem.project1.entities.Category;
import org.springframework.data.domain.Page;

public interface ICategoryService {

    Category save(Category category);

    Category get(int id);

    Page<Category> cursor (int page , int pageSize);

    Category update(Category category);

    boolean delete(int id);
}
