package com.libarymanagementsystem.project1.business.abstracts;

import com.libarymanagementsystem.project1.entities.Author;
import com.libarymanagementsystem.project1.entities.BookBorrowing;
import org.springframework.data.domain.Page;

public interface IBookBorrowingService {
    BookBorrowing save(BookBorrowing bookBorrowing);

    BookBorrowing get(int id);

    Page<BookBorrowing> cursor (int page , int pageSize);

    BookBorrowing update(Author author);

    boolean delete(int id);
}
