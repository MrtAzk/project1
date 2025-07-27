package com.libarymanagementsystem.project1.dao;

import com.libarymanagementsystem.project1.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepo extends JpaRepository<Author,Integer> {
}
