package com.libarymanagementsystem.project1.dao;

import com.libarymanagementsystem.project1.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category,Integer> {
}
