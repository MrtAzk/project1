package com.libarymanagementsystem.project1.api;

import com.libarymanagementsystem.project1.business.abstracts.IPublisherService;
import com.libarymanagementsystem.project1.core.config.modelmapper.IModelMapperService;
import com.libarymanagementsystem.project1.core.result.ResultData;
import com.libarymanagementsystem.project1.core.utiles.CreateResult;
import com.libarymanagementsystem.project1.dto.request.Author.AuthorSaveRequest;
import com.libarymanagementsystem.project1.dto.request.Author.AuthorUpdateRequest;
import com.libarymanagementsystem.project1.dto.request.Publisher.PublisherSaveRequest;
import com.libarymanagementsystem.project1.dto.request.Publisher.PublisherUpdateRequest;
import com.libarymanagementsystem.project1.dto.response.Author.AuthorResponse;
import com.libarymanagementsystem.project1.dto.response.CursorResponse;
import com.libarymanagementsystem.project1.dto.response.Publisher.PublisherResponse;
import com.libarymanagementsystem.project1.entities.Author;
import com.libarymanagementsystem.project1.entities.Publisher;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
@RestController
@RequestMapping("/v1/publishers")
public class PublisherController {
    private final IModelMapperService modelMapper;
    private final IPublisherService publisherService;

    public PublisherController(IModelMapperService modelMapper, IPublisherService publisherService) {
        this.modelMapper = modelMapper;
        this.publisherService = publisherService;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<PublisherResponse> save(@Valid @RequestBody PublisherSaveRequest publisherSaveRequest) {
        Publisher savePublisher = this.modelMapper.forRequest().map(publisherSaveRequest, Publisher.class);
        this.publisherService.save(savePublisher);

        PublisherResponse publisherResponse = this.modelMapper.forResponse().map(savePublisher, PublisherResponse.class);
        return CreateResult.created(publisherResponse);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    private ResultData<PublisherResponse> get(@PathVariable("id") int id) {
        Publisher publisher = this.publisherService.get(id);
        PublisherResponse publisherResponse = this.modelMapper.forResponse().map(publisher, PublisherResponse.class);
        return CreateResult.success(publisherResponse);

    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    private ResultData<CursorResponse<PublisherResponse>> getAll(@RequestParam(name = "page", required = false, defaultValue = "0") int page,
                                                                 @RequestParam(name = "pagesize", required = false, defaultValue = "10") int pageSize) {

        Page<Publisher> publishers = this.publisherService.cursor(page, pageSize);

        Page<PublisherResponse> publisherResponsePage = publishers.map(publisher -> this.modelMapper.forResponse().map(publishers, PublisherResponse.class));

        return CreateResult.cursor(publisherResponsePage);

    }

    @PutMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<PublisherResponse> update(@Valid @RequestBody PublisherUpdateRequest publisherUpdateRequest) {

        Publisher updatePublisher = this.modelMapper.forRequest().map(publisherUpdateRequest, Publisher.class);
        this.publisherService.update(updatePublisher);

        PublisherResponse publisherResponse = this.modelMapper.forResponse().map(updatePublisher, PublisherResponse.class);
        return CreateResult.success(publisherResponse);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<PublisherResponse> delete(@PathVariable("id") int id) {
        Publisher publisher = this.publisherService.get(id);
        boolean deletePublisher = this.publisherService.delete(id);
        PublisherResponse publisherResponse = this.modelMapper.forResponse().map(publisher, PublisherResponse.class);

        return CreateResult.deleteResult(publisherResponse);
    }
}
