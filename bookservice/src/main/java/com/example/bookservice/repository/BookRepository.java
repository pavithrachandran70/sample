package com.example.bookservice.repository;


import com.example.bookservice.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByLibraryId(Long libraryId);

    // Scenario 1: Books with same title and price range
    List<Book> findByTitleAndPriceBetween(String title, double minPrice, double maxPrice);

    // Scenario 2: Book with specific title and author
    Book findByTitleAndAuthor(String title, String author);

}

