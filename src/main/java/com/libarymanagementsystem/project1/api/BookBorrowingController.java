package com.libarymanagementsystem.project1.api;

import com.libarymanagementsystem.project1.business.abstracts.IBookBorrowingService;
import com.libarymanagementsystem.project1.business.abstracts.IBookService;
import com.libarymanagementsystem.project1.core.config.modelmapper.IModelMapperService;
import com.libarymanagementsystem.project1.core.result.ResultData;
import com.libarymanagementsystem.project1.core.utiles.CreateResult;
import com.libarymanagementsystem.project1.dto.request.BookBorrowing.BookBorrowingSaveRequest;
import com.libarymanagementsystem.project1.dto.request.BookBorrowing.BookBorrowingUpdateRequest;
import com.libarymanagementsystem.project1.dto.response.BookBorrowing.BookBorrowingResponse;
import com.libarymanagementsystem.project1.dto.response.CursorResponse;
import com.libarymanagementsystem.project1.entities.Book;
import com.libarymanagementsystem.project1.entities.BookBorrowing;
import com.libarymanagementsystem.project1.entities.Publisher;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;

@RestController
@RequestMapping("/v1/bookborrowings")
public class BookBorrowingController {

    private final IModelMapperService modelMapper;
    private final IBookBorrowingService bookBorrowingService;
    private final IBookService bookService;

    public BookBorrowingController(IModelMapperService modelMapper, IBookBorrowingService bookBorrowingService, IBookService bookService) {
        this.modelMapper = modelMapper;
        this.bookBorrowingService = bookBorrowingService;
        this.bookService = bookService;

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)

    public ResultData<BookBorrowingResponse> save(@Valid @RequestBody BookBorrowingSaveRequest bookBorrowingSaveRequest){
        BookBorrowing saveBookBorrowing =this.modelMapper.forRequest().map(bookBorrowingSaveRequest,BookBorrowing.class);

        Book book =this.bookService.get(bookBorrowingSaveRequest.getBookId());
        saveBookBorrowing.setBook(book);
        saveBookBorrowing.setBorrowingDate(LocalDate.now());
        this.bookBorrowingService.save(saveBookBorrowing);

        BookBorrowingResponse bookBorrowingResponse =this.modelMapper.forResponse().map(saveBookBorrowing,BookBorrowingResponse.class);
        return CreateResult.created(bookBorrowingResponse);

    }
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<BookBorrowingResponse>get(@PathVariable("id")int id){
        BookBorrowing bookBorrowing =this.bookBorrowingService.get(id);
        BookBorrowingResponse bookBorrowingResponse=this.modelMapper.forResponse().map(bookBorrowing,BookBorrowingResponse.class);
        return CreateResult.success(bookBorrowingResponse);
    }
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<BookBorrowingResponse>> getAll(@RequestParam(name = "page",required = false,defaultValue = "0")int page,
                                                                    @RequestParam(name = "pageSize",required = false,defaultValue = "10")int pageSize){

        Page<BookBorrowing> bookBorrowings=this.bookBorrowingService.cursor(page,pageSize);
        Page<BookBorrowingResponse> bookBorrowingResponsePage=bookBorrowings.map(bookBorrowing -> this.modelMapper.forResponse().map(bookBorrowing,BookBorrowingResponse.class));
        return CreateResult.cursor(bookBorrowingResponsePage);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public  ResultData<BookBorrowingResponse> update(@Valid @RequestBody BookBorrowingUpdateRequest bookBorrowingUpdateRequest){
        BookBorrowing updateBookBorrowing =this.modelMapper.forRequest().map(bookBorrowingUpdateRequest,BookBorrowing.class);
        Book book =this.bookService.get(bookBorrowingUpdateRequest.getBookId());
        updateBookBorrowing.setBook(book);

        this.bookBorrowingService.update(updateBookBorrowing);

        BookBorrowingResponse bookBorrowingResponse=this.modelMapper.forResponse().map(updateBookBorrowing,BookBorrowingResponse.class);
        return CreateResult.created(bookBorrowingResponse);

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public  ResultData<BookBorrowingResponse> delete (@PathVariable("id") int id) {
        BookBorrowing bookBorrowing = this.bookBorrowingService.get(id);
        boolean deleteBookBorrowing = this.bookBorrowingService.delete(id);

        BookBorrowingResponse bookBorrowingResponse = this.modelMapper.forResponse().map(bookBorrowing, BookBorrowingResponse.class);

        return CreateResult.deleteResult(bookBorrowingResponse);

    }

}
