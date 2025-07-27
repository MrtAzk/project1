package com.libarymanagementsystem.project1.dto.response.Publisher;

import com.libarymanagementsystem.project1.entities.Book;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

public class PublisherResponse {

    private  int id;


    private String name ;


    private int establishmentYear;


    private String address ;


    private List<Book> books = new ArrayList<>();
}

