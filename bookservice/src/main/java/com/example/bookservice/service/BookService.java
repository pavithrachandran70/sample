package com.example.bookservice.service;


import com.example.bookservice.entity.Book;

import java.util.List;

public interface BookService {

    Book save(Book book);

    List<Book> findAll();

    Book findById(Long id);

    Book updateById(Long id, Book updated);

    void deleteById(Long id);

    List<Book> getBooksByLibraryId(Long libraryId);

    List<Book> findByTitleAndPriceRange(String title, double minPrice, double maxPrice);
    Book findByTitleAndAuthor(String title, String author);

}

