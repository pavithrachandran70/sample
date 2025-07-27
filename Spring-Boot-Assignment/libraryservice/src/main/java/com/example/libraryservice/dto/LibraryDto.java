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

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LibraryDto {


    private Long id;
    private String name;
    private String city;
    private List<BookDto> books;


}