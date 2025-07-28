package com.libarymanagementsystem.project1.dto.request.BookBorrowing;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookBorrowingSaveRequest {


    @NotNull(message = "Kiralıyan Kişi İsimini Girmek Zorundadır")
    private  String borrowerName;

    @NotNull(message = "Kiralıyan Kişi Mail Adresi Girmek Zorundadır")
    private  String borrowerEmail;


    @NotNull(message = "Kitap İade Tarihi Giriniz")
    private LocalDate returnDate;

    @Positive
    @NotNull
    private Integer bookId;
}
