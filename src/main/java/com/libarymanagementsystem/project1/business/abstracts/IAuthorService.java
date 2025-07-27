package com.libarymanagementsystem.project1.business.abstracts;

import com.libarymanagementsystem.project1.entities.Author;
import com.libarymanagementsystem.project1.entities.Book;
import org.springframework.data.domain.Page;

public interface IAuthorService {
    Author save(Author author);

    Author get(int id);

    Page<Author> cursor (int page , int pageSize);

    Author update(Author author);

    boolean delete(int id);
}
