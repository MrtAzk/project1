package com.libarymanagementsystem.project1.dao;

import com.libarymanagementsystem.project1.entities.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublisherRepo extends JpaRepository<Publisher,Integer> {
}
