package com.libarymanagementsystem.project1.business.abstracts;

import com.libarymanagementsystem.project1.entities.Book;
import com.libarymanagementsystem.project1.entities.Publisher;
import org.springframework.data.domain.Page;

public interface IPublisherService {

    Publisher save(Publisher publisher);

    Publisher get(int id);

    Page<Publisher> cursor (int page , int pageSize);

    Publisher update(Publisher publisher);

    boolean delete(int id);
}
