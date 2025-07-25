package com.example.bookservice.repository;


import com.example.bookservice.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByLibraryId(Long libraryId);

    //  books with same title and price range
    List<Book> findByTitleAndPriceBetween(String title, double minPrice, double maxPrice);

    // book with specific title and author
    // handle the case where no book is found.
    Optional<Book> findByTitleAndAuthor(String title, String author);

}

