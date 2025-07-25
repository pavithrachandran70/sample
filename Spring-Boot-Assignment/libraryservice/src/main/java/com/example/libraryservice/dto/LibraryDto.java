package com.example.libraryservice.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.print.Book;
import java.util.List;

//public class LibraryDto {
//    private Long id;
//    private String name;
//    private String city;
//    private List<BookDto> books;
//
//}


public class LibraryDto {
    private final Long id;
    private final String name;
    private final String city;
    private final List<BookDto> books;
    public LibraryDto(Long id, String name, String city, List<BookDto> books) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.books = (books == null) ? List.of() : books;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getCity() { return city; }
    public List<BookDto> getBooks() { return books; }


}