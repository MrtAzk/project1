package com.libarymanagementsystem.project1.business.concretes;

import com.libarymanagementsystem.project1.business.abstracts.IPublisherService;
import com.libarymanagementsystem.project1.core.exception.NotFoundException;
import com.libarymanagementsystem.project1.core.utiles.Msg;
import com.libarymanagementsystem.project1.dao.PublisherRepo;
import com.libarymanagementsystem.project1.entities.Publisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PublisherManager implements IPublisherService {
    private final PublisherRepo publisherRepo;

    public PublisherManager(PublisherRepo publisherRepo) {
        this.publisherRepo = publisherRepo;
    }

    @Override
    public Publisher save(Publisher publisher) {
        return this.publisherRepo.save(publisher);
    }

    @Override
    public Publisher get(int id) {
        return this.publisherRepo.findById(id).orElseThrow(()->new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public Page<Publisher> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page,pageSize);
        return this.publisherRepo.findAll(pageable);
    }

    @Override
    public Publisher update(Publisher publisher) {
        this.get((publisher.getId()));
        return this.publisherRepo.save(publisher);
    }

    @Override
    public boolean delete(int id) {
        Publisher publisher=this.get(id);
        this.publisherRepo.delete(publisher);
        return true;
    }
}
