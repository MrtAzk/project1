package com.libarymanagementsystem.project1.dto.response.BookBorrowing;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookBorrowingResponse {

    private  int id ;


    private  String borrowerName;


    private  String borrowerEmail;


    private LocalDate borrowingDate;


    private LocalDate returnDate;


    private Integer bookId;
}
