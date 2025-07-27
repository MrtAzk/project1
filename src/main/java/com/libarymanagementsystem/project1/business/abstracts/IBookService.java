package com.libarymanagementsystem.project1.business.abstracts;

import com.libarymanagementsystem.project1.entities.Book;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IBookService {

    Book save(Book book);

    Book get(int id);

    Page<Book> cursor (int page ,int pageSize);

    Book update(Book book);

    boolean delete(int id);

}
