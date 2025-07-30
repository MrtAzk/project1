package com.libarymanagementsystem.project1.business.concretes;

import com.libarymanagementsystem.project1.business.abstracts.IBookBorrowingService;
import com.libarymanagementsystem.project1.core.exception.NotFoundException;
import com.libarymanagementsystem.project1.core.utiles.Msg;
import com.libarymanagementsystem.project1.dao.BookBorrowingRepo;
import com.libarymanagementsystem.project1.entities.BookBorrowing;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class BookBorrowingManager implements IBookBorrowingService {
    private  final BookBorrowingRepo bookBorrowingRepo;

    public BookBorrowingManager(BookBorrowingRepo bookBorrowingRepo) {
        this.bookBorrowingRepo = bookBorrowingRepo;
    }

    @Override
    public BookBorrowing save(BookBorrowing bookBorrowing) {
        return bookBorrowingRepo.save(bookBorrowing);
    }

    @Override
    public BookBorrowing get(int id) {
        return bookBorrowingRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public Page<BookBorrowing> cursor(int page, int pageSize) {
        Pageable pageable= PageRequest.of(page,pageSize);
        return this.bookBorrowingRepo.findAll(pageable);
    }

    @Override
    public BookBorrowing update(BookBorrowing bookBorrowing) {
        this.get(bookBorrowing.getId());
        bookBorrowing.setReturnDate(LocalDate.now());
        return this.save(bookBorrowing);
    }

    @Override
    public boolean delete(int id) {
        BookBorrowing bookBorrowing=this.get(id);
         this.bookBorrowingRepo.delete(bookBorrowing);
        return true;
    }
}
