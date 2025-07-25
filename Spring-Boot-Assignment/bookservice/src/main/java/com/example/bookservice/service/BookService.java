package com.example.bookservice.service;


import com.example.bookservice.dto.BookDTO;
import com.example.bookservice.entity.Book;

import java.util.List;

public interface BookService {

    BookDTO save(Book book);

    List<BookDTO> findAll();

    BookDTO findById(Long id);

    BookDTO updateById(Long id, Book book);

    void deleteById(Long id);

    List<BookDTO> getBooksByLibraryId(Long libraryId);

    BookDTO findByTitleAndAuthor(String title, String author);

    List<BookDTO> findByTitleAndPriceRange(String title, double minPrice, double maxPrice);


}

