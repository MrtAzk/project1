package com.libarymanagementsystem.project1.api;

import com.libarymanagementsystem.project1.business.abstracts.IAuthorService;
import com.libarymanagementsystem.project1.business.abstracts.IBookService;
import com.libarymanagementsystem.project1.business.abstracts.ICategoryService;
import com.libarymanagementsystem.project1.business.abstracts.IPublisherService;
import com.libarymanagementsystem.project1.core.config.modelmapper.IModelMapperService;
import com.libarymanagementsystem.project1.core.result.ResultData;
import com.libarymanagementsystem.project1.core.utiles.CreateResult;
import com.libarymanagementsystem.project1.dto.request.Book.BookSaveRequest;
import com.libarymanagementsystem.project1.dto.request.Book.BookUpdateRequest;
import com.libarymanagementsystem.project1.dto.response.Book.BookResponse;
import com.libarymanagementsystem.project1.dto.response.CursorResponse;
import com.libarymanagementsystem.project1.entities.Author;
import com.libarymanagementsystem.project1.entities.Book;
import com.libarymanagementsystem.project1.entities.Category;
import com.libarymanagementsystem.project1.entities.Publisher;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/books")
public class BookController {
    private final IBookService bookService;
    private final IModelMapperService modelMapper;
    private final IAuthorService authorService;
    private final IPublisherService publisherService;
    private final ICategoryService categoryService;

    public BookController(IBookService bookService,
                          IModelMapperService modelMapper,
                          IAuthorService authorService,
                          IPublisherService publisherService,
                          ICategoryService categoryService
    )
    {
        this.bookService = bookService;
        this.modelMapper = modelMapper;
        this.authorService = authorService;
        this.publisherService = publisherService;
        this.categoryService = categoryService;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<BookResponse> save (@Valid @RequestBody BookSaveRequest bookSaveRequest){
        Book saveBook= this.modelMapper.forRequest().map(bookSaveRequest,Book.class);

        Author author = this.authorService.get(bookSaveRequest.getAuthorId());
        saveBook.setAuthor(author);

        Publisher publisher = this.publisherService.get(bookSaveRequest.getPublisherId());
        saveBook.setPublisher(publisher);

        List<Category> categories = new ArrayList<>();
        for (Integer categoryId :bookSaveRequest.getCategoriesId()){
            Category category = this.categoryService.get(categoryId);
            categories.add(category);
        }
        saveBook.setCategories(categories);

        this.bookService.save(saveBook);

        BookResponse bookResponse =this.modelMapper.forResponse().map(saveBook,BookResponse.class);
       return CreateResult.created(bookResponse);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public   ResultData<BookResponse > get(@PathVariable("id") int id ){
        Book book=this.bookService.get(id);
        BookResponse bookResponse =this.modelMapper.forResponse().map(book,BookResponse.class);
        return CreateResult.success(bookResponse);

    }
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<BookResponse>> getAll(@RequestParam(name = "page",required = false,defaultValue = "0") int page,
                                              @RequestParam(name = "pagesize",required = false,defaultValue = "10")int pageSize)
    {

        Page<Book>books=this.bookService.cursor(page,pageSize);

        Page<BookResponse> bookResponsePage =books.map(book -> this.modelMapper.forResponse().map(book, BookResponse.class));

        return CreateResult.cursor(bookResponsePage);

    }

    @PutMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<BookResponse> update (@Valid @RequestBody BookUpdateRequest bookUpdateRequest){

        Book updateBook=this.modelMapper.forRequest().map(bookUpdateRequest,Book.class);
        this.bookService.update(updateBook);

        BookResponse bookResponse =this.modelMapper.forResponse().map(updateBook,BookResponse.class);
        return CreateResult.success(bookResponse);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<BookResponse> delete(@PathVariable("id")int id){
        Book book=this.bookService.get(id);
        boolean deleteBook =this.bookService.delete(id);
        BookResponse bookResponse=this.modelMapper.forResponse().map(book,BookResponse.class);

        return  CreateResult.deleteResult(bookResponse);
    }

}
