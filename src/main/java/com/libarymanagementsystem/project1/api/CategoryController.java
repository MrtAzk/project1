package com.libarymanagementsystem.project1.api;

import com.libarymanagementsystem.project1.business.abstracts.ICategoryService;
import com.libarymanagementsystem.project1.core.config.modelmapper.IModelMapperService;
import com.libarymanagementsystem.project1.core.result.ResultData;
import com.libarymanagementsystem.project1.core.utiles.CreateResult;
import com.libarymanagementsystem.project1.dto.request.Book.BookUpdateRequest;
import com.libarymanagementsystem.project1.dto.request.Category.CategorySaveRequest;
import com.libarymanagementsystem.project1.dto.request.Category.CategoryUpdateRequest;
import com.libarymanagementsystem.project1.dto.response.Book.BookResponse;
import com.libarymanagementsystem.project1.dto.response.Category.CategoryResponse;
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
@RequestMapping("/v1/categories")
public class CategoryController {
    private final ICategoryService categoryService;
    private  final IModelMapperService modelMapper;

    public CategoryController(ICategoryService categoryService, IModelMapperService modelMapper) {
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
    }


    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<CategoryResponse> save (@Valid @RequestBody CategorySaveRequest categorySaveRequest){
        Category saveCategory= this.modelMapper.forRequest().map(categorySaveRequest,Category.class);


        this.categoryService.save(saveCategory);

        CategoryResponse categoryResponse =this.modelMapper.forResponse().map(saveCategory,CategoryResponse.class);
        return CreateResult.created(categoryResponse);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public   ResultData<CategoryResponse > get(@PathVariable("id") int id ){
        Category category=this.categoryService.get(id);
        CategoryResponse categoryResponse =this.modelMapper.forResponse().map(category,CategoryResponse.class);
        return CreateResult.success(categoryResponse);

    }
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<CategoryResponse>> getAll(@RequestParam(name = "page",required = false,defaultValue = "0") int page,
                                                           @RequestParam(name = "pagesize",required = false,defaultValue = "10")int pageSize)
    {

        Page<Category> categories=this.categoryService.cursor(page,pageSize);

        Page<CategoryResponse> categoryResponsePage =categories.map(book -> this.modelMapper.forResponse().map(categories, CategoryResponse.class));

        return CreateResult.cursor(categoryResponsePage);

    }

    @PutMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<CategoryResponse> update (@Valid @RequestBody CategoryUpdateRequest categoryUpdateRequest){

        Category updateCategory=this.modelMapper.forRequest().map(categoryUpdateRequest,Category.class);
        this.categoryService.update(updateCategory);

        CategoryResponse categoryResponse =this.modelMapper.forResponse().map(updateCategory,CategoryResponse.class);
        return CreateResult.success(categoryResponse);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CategoryResponse> delete(@PathVariable("id")int id){
        Category category=this.categoryService.get(id);
        boolean deleteBook =this.categoryService.delete(id);
        CategoryResponse categoryResponse=this.modelMapper.forResponse().map(category,CategoryResponse.class);

        return  CreateResult.deleteResult(categoryResponse);
    }
}
