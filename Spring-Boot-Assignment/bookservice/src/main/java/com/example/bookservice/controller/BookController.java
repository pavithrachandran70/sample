package com.example.bookservice.controller;


import com.example.bookservice.entity.Book;
import com.example.bookservice.service.BookService;
import com.example.bookservice.service.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    @Autowired
    //bookservice
    private BookService service;

    @PostMapping
    //createbook
//    public Book add(@RequestBody Book book)
   public Book createBook(@RequestBody Book book) {

        return service.save(book);
    }

    @GetMapping
    //getallbooks
//    public List<Book> list()
    public List<Book> getAllBooks()
    {

        return service.findAll();
    }

    @GetMapping("/{id}")
    //getbookbyid
//    public Book get(@PathVariable Long id)
    public Book getBookById(@PathVariable Long id) {

        return service.findById(id);
    }

    @PutMapping("/{id}")
    //ypdatebookbyid
//    public Book update(@PathVariable Long id, @RequestBody Book book)
    public Book updateBookById(@PathVariable Long id, @RequestBody Book book){

        return service.updateById(id, book);
    }

    @DeleteMapping("/{id}")
    //deletebookbyid
//    public void delete(@PathVariable Long id)
    public void deleteBookById(@PathVariable Long id){

        service.deleteById(id);
    }

    @GetMapping("/library/{libraryId}")
    public List<Book> getBooksByLibraryId(@PathVariable Long libraryId) {
        return service.getBooksByLibraryId(libraryId);
    }


    // Scenario 1: Get books by title and price range
    @GetMapping("/search")
    public List<Book> searchBooksByTitleAndPrice(
            @RequestParam String title,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice) {

        if (minPrice == null || maxPrice == null) {
            throw new IllegalArgumentException("Both minPrice and maxPrice are required.");
        }

        return service.findByTitleAndPriceRange(title, minPrice, maxPrice);
    }

    // Scenario 2: Get a book by title and author
    @GetMapping("/search/author")
    public Book searchBookByTitleAndAuthor(
            @RequestParam String title,
            @RequestParam String author) {
        return service.findByTitleAndAuthor(title, author);
    }


}
