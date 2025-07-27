package com.libarymanagementsystem.project1.api;

import com.libarymanagementsystem.project1.business.abstracts.IAuthorService;
import com.libarymanagementsystem.project1.core.config.modelmapper.IModelMapperService;
import com.libarymanagementsystem.project1.core.result.ResultData;
import com.libarymanagementsystem.project1.core.utiles.CreateResult;
import com.libarymanagementsystem.project1.dto.request.Author.AuthorSaveRequest;
import com.libarymanagementsystem.project1.dto.request.Author.AuthorUpdateRequest;
import com.libarymanagementsystem.project1.dto.response.Author.AuthorResponse;
import com.libarymanagementsystem.project1.dto.response.CursorResponse;
import com.libarymanagementsystem.project1.entities.Author;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
@RestController
@RequestMapping("/v1/authors")
public class AuthorController {
    private final IAuthorService authorService;
    private final IModelMapperService modelMapper;

    public AuthorController(IAuthorService authorService, IModelMapperService modelMapper) {
        this.authorService = authorService;
        this.modelMapper = modelMapper;
    }


    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<AuthorResponse> save (@Valid @RequestBody AuthorSaveRequest authorSaveRequest){
        Author saveAuthor=this.modelMapper.forRequest().map(authorSaveRequest, Author.class);
        this.authorService.save(saveAuthor);

        AuthorResponse authorResponse =this.modelMapper.forResponse().map(saveAuthor,AuthorResponse.class);
        return CreateResult.created(authorResponse);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    private  ResultData<AuthorResponse > get(@PathVariable("id") int id ){
        Author author=this.authorService.get(id);
        AuthorResponse authorResponse =this.modelMapper.forResponse().map(author,AuthorResponse.class);
        return CreateResult.success(authorResponse);

    }
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    private ResultData<CursorResponse<AuthorResponse>> getAll(@RequestParam(name = "page",required = false,defaultValue = "0") int page,
                                                            @RequestParam(name = "pagesize",required = false,defaultValue = "10")int pageSize)
    {

        Page<Author> authors=this.authorService.cursor(page,pageSize);

        Page<AuthorResponse> authorResponsePage =authors.map(author -> this.modelMapper.forResponse().map(author, AuthorResponse.class));

        return CreateResult.cursor(authorResponsePage);

    }

    @PutMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<AuthorResponse> update (@Valid @RequestBody AuthorUpdateRequest authorUpdateRequest){

        Author updateAuthor=this.modelMapper.forRequest().map(authorUpdateRequest,Author.class);
        this.authorService.update(updateAuthor);

        AuthorResponse authorResponse =this.modelMapper.forResponse().map(updateAuthor,AuthorResponse.class);
        return CreateResult.success(authorResponse);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AuthorResponse> delete(@PathVariable("id")int id){
        Author author=this.authorService.get(id);
        boolean deleteAuthor =this.authorService.delete(id);
        AuthorResponse authorResponse=this.modelMapper.forResponse().map(author,AuthorResponse.class);

        return  CreateResult.deleteResult(authorResponse);
    }
}
